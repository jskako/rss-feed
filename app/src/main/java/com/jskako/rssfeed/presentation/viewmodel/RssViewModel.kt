package com.jskako.rssfeed.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.jskako.rssfeed.core.utils.RSS_WORKER_KEY
import com.jskako.rssfeed.data.worker.RssWorker
import com.jskako.rssfeed.domain.mapper.toRssChannel
import com.jskako.rssfeed.domain.mapper.toRssItem
import com.jskako.rssfeed.domain.model.database.RssChannel
import com.jskako.rssfeed.domain.model.database.RssItem
import com.jskako.rssfeed.domain.usecase.rss.api.ApiUseCases
import com.jskako.rssfeed.domain.usecase.rss.database.PreferencesUseCases
import com.jskako.rssfeed.presentation.delegate.database.DatabaseDelegate
import com.jskako.rssfeed.presentation.event.RssEvent
import com.jskako.rssfeed.presentation.state.AddingProcessState
import com.jskako.rssfeed.presentation.state.RssWorkerState
import com.jskako.rssfeed.presentation.utils.SELECTED_CHANNEL_KEY
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class RssViewModel(
    private val context: Application,
    private val apiUseCases: ApiUseCases,
    private val databaseDelegate: DatabaseDelegate,
    private val preferencesUseCases: PreferencesUseCases
) : ViewModel() {

    init {
        loadSelectedChannel()
    }

    fun onRssEvent(event: RssEvent) {
        when (event) {
            is RssEvent.DeleteChannel -> {
                deleteRssChannel(rss = event.rss)
            }

            is RssEvent.FetchRssFeed -> {
                fetchRssFeed(
                    rss = event.rss,
                    runRssExistCheck = event.runRssExistCheck,
                    setSelected = event.setSelected
                )
            }

            is RssEvent.HasBeenRead -> {
                hasBeenRead(guid = event.guid, hasBeenRead = event.hasBeenRead)
            }

            is RssEvent.SelectChannel -> {
                selectChannel(channel = event.channel)
            }

            is RssEvent.UpdateNotification -> {
                updateNotification(rss = event.rss, isEnabled = event.isEnabled)
            }

            is RssEvent.UpdateFavorite -> {
                updateFavoriteStatus(guid = event.guid, isFavorite = event.isFavorite)
            }

            is RssEvent.FetchRssFeeds -> {}
            is RssEvent.CancelWork -> {
                cancelRssWorker(rss = event.rss)
            }

            is RssEvent.DoWork -> {
                scheduleRssWorker(rss = event.rss)
            }

            is RssEvent.ScheduleWork -> {
                schedulePeriodicRssWorker(rss = event.rss)
            }
        }
    }

    private val _rssWorkerState = MutableStateFlow<RssWorkerState>(RssWorkerState.Idle)
    val rssWorkerState: StateFlow<RssWorkerState> = _rssWorkerState

    fun scheduleRssWorker(rss: String) {
        viewModelScope.launch {
            _rssWorkerState.value = RssWorkerState.Running

            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val inputData = Data.Builder()
                .putString(RSS_WORKER_KEY, rss)
                .build()

            val rssWorkRequest = OneTimeWorkRequestBuilder<RssWorker>()
                .setInputData(inputData)
                .setConstraints(constraints)
                .addTag(rss)
                .build()

            WorkManager.getInstance(context).enqueue(rssWorkRequest)

            _rssWorkerState.value = RssWorkerState.Fetched
        }
    }

    fun schedulePeriodicRssWorker(rss: String) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()

        val inputData = Data.Builder()
            .putString(RSS_WORKER_KEY, rss)
            .build()

        val rssPeriodicWorkRequest = PeriodicWorkRequestBuilder<RssWorker>(
            1, TimeUnit.HOURS
        )
            .setInputData(inputData)
            .setConstraints(constraints)
            .addTag(rss)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            rss,
            ExistingPeriodicWorkPolicy.KEEP,
            rssPeriodicWorkRequest
        )

        _rssWorkerState.value = RssWorkerState.Scheduled
    }

    fun cancelRssWorker(rss: String) {
        viewModelScope.launch {
            WorkManager.getInstance(context).cancelAllWorkByTag(rss)
            _rssWorkerState.value = RssWorkerState.Unscheduled
        }
    }

    private val _rssChannels = databaseDelegate.getRssChannels()
    val rssChannels: StateFlow<List<RssChannel>?> = _rssChannels
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = null
        )

    private val _selectedChannel = MutableStateFlow<RssChannel?>(null)
    val selectedChannel: StateFlow<RssChannel?> = _selectedChannel

    private fun loadSelectedChannel() {
        viewModelScope.launch {
            preferencesUseCases.getPreference(SELECTED_CHANNEL_KEY).let { rss ->
                rss?.let {
                    _selectedChannel.value = databaseDelegate.getRssChannel(rss = it)
                }
            }
        }
    }

    fun observeUnreadCount(rss: String): Flow<Int> {
        return databaseDelegate.getUnreadItemsCount(rss)
    }

    private fun hasBeenRead(guid: String, hasBeenRead: Boolean = true) = viewModelScope.launch {
        databaseDelegate.updateReadStatus(guid = guid, hasBeenRead = hasBeenRead)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _rssItems = _selectedChannel
        .filterNotNull()
        .flatMapLatest { channel ->
            databaseDelegate.getRssItems(channel.rss)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )

    val rssItems: StateFlow<List<RssItem>> = _rssItems

    private val _addingProcessState =
        MutableStateFlow<AddingProcessState>(AddingProcessState.NotStarted)
    val addingProcessState: StateFlow<AddingProcessState> = _addingProcessState

    private fun savePreference(key: String, value: String) {
        viewModelScope.launch {
            preferencesUseCases.savePreference(key, value)
        }
    }

    private fun selectChannel(channel: RssChannel) {
        _selectedChannel.value = channel
        savePreference(
            key = SELECTED_CHANNEL_KEY,
            value = channel.rss
        )
    }

    private fun deleteRssChannel(rss: String) = viewModelScope.launch {
        databaseDelegate.deleteRssChannel(rss = rss)
    }

    private fun updateNotification(rss: String, isEnabled: Boolean) {
        viewModelScope.launch {
            databaseDelegate.updateNotification(rss, isEnabled)
        }
    }

    private fun updateFavoriteStatus(guid: String, isFavorite: Boolean) {
        viewModelScope.launch {
            databaseDelegate.updateFavoriteStatus(guid = guid, isFavorite = isFavorite)
        }
    }

    private fun fetchRssFeed(
        rss: String,
        runRssExistCheck: Boolean = true,
        setSelected: Boolean = false
    ) = viewModelScope.launch {
        _addingProcessState.value = AddingProcessState.FetchingData
        runCatching {
            val feeds = apiUseCases.fetchRssFeeds(
                rss = rss,
                runRssExistCheck = runRssExistCheck
            )

            val rssChannel = feeds.rssApiChannel.toRssChannel(
                isNotificationEnabled = databaseDelegate.isNotificationEnabled(rss = rss)
            )

            if (setSelected) {
                selectChannel(channel = rssChannel)
            }

            databaseDelegate.addToDatabase(
                rss = rss,
                rssChannel = rssChannel,
                rssItems = feeds.rssApiItems.map { item ->
                    item.toRssItem(
                        hasBeenRead = databaseDelegate.hasBeenRead(guid = item.guid),
                        isFavorite = databaseDelegate.isFavorite(guid = item.guid)
                    )
                }
            )
        }.fold(
            onSuccess = {
                _addingProcessState.value = AddingProcessState.Done(
                    result = Result.success(null)
                )
            },
            onFailure = { error ->
                _addingProcessState.value = AddingProcessState.Done(
                    Result.failure(error)
                )
            }
        )
    }
}
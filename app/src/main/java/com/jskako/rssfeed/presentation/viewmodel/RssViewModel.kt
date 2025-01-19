package com.jskako.rssfeed.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jskako.rssfeed.domain.model.database.RssChannel
import com.jskako.rssfeed.domain.model.database.RssItem
import com.jskako.rssfeed.domain.usecase.rss.database.PreferencesUseCases
import com.jskako.rssfeed.presentation.delegate.database.DatabaseDelegate
import com.jskako.rssfeed.presentation.delegate.worker.WorkerDelegate
import com.jskako.rssfeed.presentation.event.RssEvent
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

class RssViewModel(
    private val databaseDelegate: DatabaseDelegate,
    private val workerDelegate: WorkerDelegate,
    private val preferencesUseCases: PreferencesUseCases
) : ViewModel() {

    init {
        loadSelectedRss()
    }

    fun onRssEvent(event: RssEvent) {
        when (event) {
            is RssEvent.DeleteChannel -> {
                deleteRssChannel(rss = event.rss)
            }

            is RssEvent.FetchRssFeed -> {
                viewModelScope.launch {

                    if (event.setSelected) {
                        selectChannel(rss = event.rss)
                    }

                    workerDelegate.scheduleRssWorker(
                        rss = event.rss,
                        runRssExistCheck = event.runRssExistCheck
                    )
                }
            }

            is RssEvent.HasBeenRead -> {
                hasBeenRead(guid = event.guid, hasBeenRead = event.hasBeenRead)
            }

            is RssEvent.SelectChannel -> {
                selectChannel(rss = event.rss)
            }

            is RssEvent.UpdateNotification -> {
                updateNotification(rss = event.rss, isEnabled = event.isEnabled)
            }

            is RssEvent.UpdateFavorite -> {
                updateFavoriteStatus(guid = event.guid, isFavorite = event.isFavorite)
            }

            is RssEvent.FetchRssFeeds -> {}
            is RssEvent.CancelWork -> {
                viewModelScope.launch {
                    workerDelegate.cancelRssWorker(rss = event.rss)
                }
            }

            is RssEvent.ScheduleWork -> {
                viewModelScope.launch {
                    workerDelegate.schedulePeriodicRssWorker(rss = event.rss)
                }
            }
        }
    }

    val rssWorkerState: StateFlow<RssWorkerState> = workerDelegate.rssWorkerState
    private val _rssChannels = databaseDelegate.getRssChannels()

    val rssChannels: StateFlow<List<RssChannel>?> = _rssChannels
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = null
        )

    private val _selectedRss = MutableStateFlow<String?>(null)
    val selectedRss: StateFlow<String?> = _selectedRss

    private fun loadSelectedRss() {
        viewModelScope.launch {
            preferencesUseCases.getPreference(SELECTED_CHANNEL_KEY).let { rss ->
                _selectedRss.value = rss
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
    private val _rssItems = _selectedRss
        .filterNotNull()
        .flatMapLatest { rss ->
            databaseDelegate.getRssItems(rss)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )

    val rssItems: StateFlow<List<RssItem>> = _rssItems

    private fun savePreference(key: String, value: String) {
        viewModelScope.launch {
            preferencesUseCases.savePreference(key, value)
        }
    }

    private fun selectChannel(rss: String) {
        _selectedRss.value = rss
        savePreference(
            key = SELECTED_CHANNEL_KEY,
            value = rss
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
}
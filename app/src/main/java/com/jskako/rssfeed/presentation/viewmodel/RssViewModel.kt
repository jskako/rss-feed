package com.jskako.rssfeed.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jskako.rssfeed.domain.mapper.toRssChannel
import com.jskako.rssfeed.domain.mapper.toRssItem
import com.jskako.rssfeed.domain.model.database.RssChannel
import com.jskako.rssfeed.domain.model.database.RssItem
import com.jskako.rssfeed.domain.usecase.rss.api.ApiUseCases
import com.jskako.rssfeed.domain.usecase.rss.database.PreferencesUseCases
import com.jskako.rssfeed.presentation.delegate.database.DatabaseDelegate
import com.jskako.rssfeed.presentation.state.AddingProcessState
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
    private val apiUseCases: ApiUseCases,
    private val databaseDelegate: DatabaseDelegate,
    private val preferencesUseCases: PreferencesUseCases
) : ViewModel() {

    init {
        loadSelectedChannel()
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

    fun hasBeenRead(guid: String, hasBeenRead: Boolean = true) = viewModelScope.launch {
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

    fun selectChannel(channel: RssChannel) {
        _selectedChannel.value = channel
        savePreference(
            key = SELECTED_CHANNEL_KEY,
            value = channel.rss
        )
    }

    fun deleteRssChannels(rss: String) = viewModelScope.launch {
        databaseDelegate.deleteRssChannel(rss = rss)
    }

    fun updateNotification(rss: String, isEnabled: Boolean) {
        viewModelScope.launch {
            databaseDelegate.updateNotification(rss, isEnabled)
        }
    }

    fun fetchRssFeed(
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
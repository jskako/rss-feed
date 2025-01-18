package com.jskako.rssfeed.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jskako.rssfeed.domain.mapper.toRssChannel
import com.jskako.rssfeed.domain.mapper.toRssItem
import com.jskako.rssfeed.domain.model.database.RssChannel
import com.jskako.rssfeed.domain.usecase.rss.api.ApiUseCases
import com.jskako.rssfeed.presentation.delegate.database.DatabaseDelegate
import com.jskako.rssfeed.presentation.state.AddingProcessState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RssViewModel(
    private val apiUseCases: ApiUseCases,
    private val databaseDelegate: DatabaseDelegate
) : ViewModel() {

    private val _rssChannels = databaseDelegate.getRssChannels()
    val rssChannels: StateFlow<List<RssChannel>?> = _rssChannels
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = null
        )

    private val _addingProcessState =
        MutableStateFlow<AddingProcessState>(AddingProcessState.NotStarted)
    val addingProcessState: StateFlow<AddingProcessState> = _addingProcessState

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
        runRssExistCheck: Boolean = true
    ) = viewModelScope.launch {
        _addingProcessState.value = AddingProcessState.FetchingData
        runCatching {
            val feeds = apiUseCases.fetchRssFeeds(
                rss = rss,
                runRssExistCheck = runRssExistCheck
            )

            databaseDelegate.addToDatabase(
                rss = rss,
                rssChannel = feeds.rssApiChannel.toRssChannel(
                    isNotificationEnabled = databaseDelegate.isNotificationEnabled(rss = rss)
                ),
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
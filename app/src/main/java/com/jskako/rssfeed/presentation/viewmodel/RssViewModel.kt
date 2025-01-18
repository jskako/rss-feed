package com.jskako.rssfeed.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jskako.rssfeed.domain.mapper.toRssChannel
import com.jskako.rssfeed.domain.mapper.toRssItems
import com.jskako.rssfeed.domain.model.database.RssChannel
import com.jskako.rssfeed.domain.usecase.rss.api.ApiUseCases
import com.jskako.rssfeed.domain.usecase.rss.database.DatabaseChannelUseCases
import com.jskako.rssfeed.presentation.delegate.database.DatabaseDelegate
import com.jskako.rssfeed.presentation.state.AddingProcessState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RssViewModel(
    private val apiUseCases: ApiUseCases,
    private val databaseDelegate: DatabaseDelegate,
    private val databaseChannelUseCases: DatabaseChannelUseCases,
) : ViewModel() {

    private val _rssChannels = databaseChannelUseCases.getRssChannels()
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
        databaseChannelUseCases.deleteRssChannel(url = rss)
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
            databaseDelegate.run {

                val feeds = apiUseCases.fetchRssFeeds(
                    rss = rss,
                    runRssExistCheck = runRssExistCheck
                )

                if (isChannelUpdated(
                        rss = rss,
                        currentDate = feeds.rssApiChannel.lastBuildDate
                    )
                ) {
                    addChannelToDatabase(
                        rssChannel = feeds.rssApiChannel.toRssChannel(
                            databaseChannelUseCases = databaseChannelUseCases
                        )
                    )
                    addItemsToDatabase(
                        rssItems = feeds.rssApiItems.toRssItems(
                            databaseChannelUseCases = databaseChannelUseCases
                        )
                    )
                }
            }
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
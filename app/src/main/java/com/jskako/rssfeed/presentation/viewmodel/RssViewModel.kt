package com.jskako.rssfeed.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.jskako.rssfeed.domain.mapper.toRssChannel
import com.jskako.rssfeed.domain.mapper.toRssItems
import com.jskako.rssfeed.domain.model.database.RssChannel
import com.jskako.rssfeed.domain.usecase.rss.api.ApiUseCases
import com.jskako.rssfeed.domain.usecase.rss.database.DatabaseChannelUseCases
import com.jskako.rssfeed.domain.usecase.rss.database.DatabaseItemUseCases
import com.jskako.rssfeed.presentation.state.AddingProcessState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RssViewModel(
    private val apiUseCases: ApiUseCases,
    databaseChannelUseCases: DatabaseChannelUseCases,
    databaseItemUseCases: DatabaseItemUseCases
) : DatabaseRssViewModel(databaseChannelUseCases, databaseItemUseCases) {

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

    fun fetchRssFeed(
        rss: String,
        runRssExistCheck: Boolean = true
    ) = viewModelScope.launch {
        _addingProcessState.value = AddingProcessState.FetchingData
        runCatching {
            if (runRssExistCheck && channelExist(rss)) {
                throw IllegalArgumentException("RSS already added: $rss")
            }

            if (!apiUseCases.isUrlReachable(rss)) {
                throw IllegalArgumentException("The RSS link is not reachable: $rss")
            }

            val feeds = apiUseCases.fetchRssFeeds(rss = rss)
                ?: throw Exception("Failed to fetch feeds for $rss")

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
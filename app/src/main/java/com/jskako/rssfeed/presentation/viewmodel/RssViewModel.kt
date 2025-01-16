package com.jskako.rssfeed.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.jskako.rssfeed.domain.model.RssChannel
import com.jskako.rssfeed.domain.usecase.rss.api.ApiUseCases
import com.jskako.rssfeed.domain.usecase.rss.database.DatabaseUseCases
import com.jskako.rssfeed.presentation.state.AddingProcessState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RssViewModel(
    private val apiUseCases: ApiUseCases,
    databaseUseCases: DatabaseUseCases
) : BaseRssViewModel(databaseUseCases) {

    private val _rssChannels = databaseUseCases.getRssChannels()
    val rssChannels: StateFlow<List<RssChannel>?> = _rssChannels
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = null
        )

    private val _addingProcessState =
        MutableStateFlow<AddingProcessState>(AddingProcessState.NotStarted)
    val addingProcessState: StateFlow<AddingProcessState> = _addingProcessState

    fun deleteRssChannels(rssLink: String) = viewModelScope.launch {
        databaseUseCases.deleteRssChannel(url = rssLink)
    }

    fun fetchRssFeed(
        rssLink: String,
        runRssExistCheck: Boolean = true,
        onDone: () -> Unit
    ) = viewModelScope.launch {
        _addingProcessState.value = AddingProcessState.FetchingData

        runCatching {
            if (runRssExistCheck && channelExist(rssLink)) {
                throw IllegalArgumentException("RSS already added: $rssLink")
            }

            if (!apiUseCases.isUrlReachable(rssLink)) {
                throw IllegalArgumentException("The RSS link is not reachable: $rssLink")
            }

            val feeds = apiUseCases.fetchRssFeeds(link = rssLink)
                ?: throw Exception("Failed to fetch feeds for $rssLink")

            if (isChannelUpdated(rssLink = rssLink, currentDate = feeds.rssChannel.lastBuildDate)) {
                addChannelToDatabase(rssChannel = feeds.rssChannel)
                addItemsToDatabase(rssItems = feeds.rssItems)
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
        onDone()
    }
}
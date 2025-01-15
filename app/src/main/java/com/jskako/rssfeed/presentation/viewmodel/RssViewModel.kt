package com.jskako.rssfeed.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jskako.rssfeed.domain.model.RssChannel
import com.jskako.rssfeed.domain.usecase.rss.api.ApiUseCases
import com.jskako.rssfeed.domain.usecase.rss.database.DatabaseUseCases
import com.jskako.rssfeed.presentation.state.AddingProcessState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.Instant

class RssViewModel(
    private val apiUseCases: ApiUseCases,
    private val databaseUseCases: DatabaseUseCases
) : ViewModel() {

    private val _rssChannels = MutableStateFlow<List<RssChannel>>(emptyList())
    val rssChannels: StateFlow<List<RssChannel>> get() = _rssChannels

    private val _addingProcessState =
        MutableStateFlow<AddingProcessState>(AddingProcessState.NotStarted)
    val addingProcessState: StateFlow<AddingProcessState> = _addingProcessState

    init {
        loadRssChannels()
    }

    private fun loadRssChannels() = viewModelScope.launch {
        databaseUseCases.getRssChannels().collect { items ->
            _rssChannels.value = items
        }
    }

    fun fetchRssFeed(rssLink: String) = viewModelScope.launch {
        _addingProcessState.value = AddingProcessState.FetchingData

        runCatching {
            if (!apiUseCases.isUrlReachable(rssLink)) {
                throw IllegalArgumentException("The RSS link is not reachable: $rssLink")
            }
            val feeds = apiUseCases.fetchRssFeeds(link = rssLink)
                ?: throw Exception("Failed to fetch feeds for $rssLink")
            if (isNewerBuildDate(rssLink, feeds.rssChannel.lastBuildDate)) {
                addChannelDatabase(rssChannel = feeds.rssChannel)
            }
        }.fold(
            onSuccess = {
                _addingProcessState.value = AddingProcessState.Done(succeed = true)
            },
            onFailure = { error ->
                Log.e("Error", "$error")
                _addingProcessState.value = AddingProcessState.Done(
                    succeed = false,
                    message = error.localizedMessage ?: "An unknown error occurred"
                )
            }
        )
    }

    private suspend fun isNewerBuildDate(
        rssLink: String,
        currentDate: Instant?
    ) = databaseUseCases.getLastBuildDate(url = rssLink)?.isBefore(currentDate) ?: true

    private suspend fun addChannelDatabase(
        rssChannel: RssChannel
    ) = databaseUseCases.insertRssChannelUseCase(rssChannel = rssChannel)
}
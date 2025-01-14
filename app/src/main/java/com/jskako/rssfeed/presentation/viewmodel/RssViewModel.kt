package com.jskako.rssfeed.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jskako.rssfeed.domain.model.RssChannel
import com.jskako.rssfeed.domain.model.RssResponse
import com.jskako.rssfeed.domain.usecase.rss.api.ApiUseCases
import com.jskako.rssfeed.domain.usecase.rss.database.DatabaseUseCases
import com.jskako.rssfeed.presentation.state.AddingProcessState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

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

    fun addRssFeed(rssLink: String) = viewModelScope.launch {
        _addingProcessState.value = AddingProcessState.FetchingData
        runCatching {
            if (!apiUseCases.isUrlReachable(rssLink)) {
                throw IllegalArgumentException("Invalid RSS link")
            }

            val feeds = apiUseCases.fetchRssFeeds(link = rssLink)
                ?: throw Exception("Failed to fetch feeds")

            // Add feeds to the database
            addToDatabase(feeds = feeds)
        }.onSuccess {
            _addingProcessState.value = AddingProcessState.Done(
                succeed = true
            )
        }.onFailure { error ->
            _addingProcessState.value = AddingProcessState.Done(
                succeed = false,
                message = error.message
            )
        }
    }

    private suspend fun addToDatabase(feeds: RssResponse) {
        /**
         * TODO
         * Check if channel has newer date than fetched one
         * If yes skip, if not add to database, if doesn't exist add to database
         *
         * if channel has newer date add items but check date also
         * if channel doesn't exist add all items
         */
    }

    fun testFetch() {
        viewModelScope.launch {
            apiUseCases.fetchRssFeeds(
                link = TEST_1
            )
        }
    }
}

private const val TEST_1 = "https://news.yahoo.com/rss/us"
private const val TEST_2 = "https://abcnews.go.com/abcnews/usheadlines"
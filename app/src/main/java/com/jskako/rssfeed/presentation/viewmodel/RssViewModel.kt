package com.jskako.rssfeed.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jskako.rssfeed.domain.model.RssChannel
import com.jskako.rssfeed.domain.usecase.rss.api.ApiUseCases
import com.jskako.rssfeed.domain.usecase.rss.feed.RssUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RssViewModel(
    private val apiUseCases: ApiUseCases,
    private val rssUseCases: RssUseCases
) : ViewModel() {

    private val _rssFeeds = MutableStateFlow<List<RssChannel>>(emptyList())
    val rssChannels: StateFlow<List<RssChannel>> get() = _rssFeeds

    init {
        loadRssFeeds()
    }

    private fun loadRssFeeds() {
        viewModelScope.launch {
            rssUseCases.getRssFeeds().collect { items ->
                _rssFeeds.value = items
            }
        }
    }

    fun testFetch() {
        viewModelScope.launch {
            apiUseCases.fetchRssFeeds(
                link = "https://abcnews.go.com/abcnews/usheadlines"
            )
        }
    }
}
package com.jskako.rssfeed.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jskako.rssfeed.domain.model.RssFeed
import com.jskako.rssfeed.domain.usecase.rss.RssUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RssViewModel(
    private val rssUseCases: RssUseCases
) : ViewModel() {

    private val _rssFeeds = MutableStateFlow<List<RssFeed>>(emptyList())
    val rssFeeds: StateFlow<List<RssFeed>> get() = _rssFeeds

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
}
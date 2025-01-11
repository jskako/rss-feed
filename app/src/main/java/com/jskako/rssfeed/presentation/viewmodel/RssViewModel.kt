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

    private val _rssItems = MutableStateFlow<List<RssFeed>>(emptyList())
    val rssItems: StateFlow<List<RssFeed>> get() = _rssItems

    init {
        loadRssItems()
    }

    private fun loadRssItems() {
        viewModelScope.launch {
            rssUseCases.getRssItems().collect { items ->
                _rssItems.value = items
            }
        }
    }
}
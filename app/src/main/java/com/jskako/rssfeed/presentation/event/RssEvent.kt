package com.jskako.rssfeed.presentation.event

import com.jskako.rssfeed.domain.model.database.RssChannel

sealed interface RssEvent {
    data class HasBeenRead(val guid: String, val hasBeenRead: Boolean = true) : RssEvent
    data class SelectChannel(val channel: RssChannel) : RssEvent
    data class DeleteChannel(val rss: String) : RssEvent
    data class UpdateNotification(val rss: String, val isEnabled: Boolean) : RssEvent
    data class UpdateFavorite(val guid: String, val isFavorite: Boolean) : RssEvent
    data class FetchRssFeed(
        val rss: String,
        val runRssExistCheck: Boolean = true,
        val setSelected: Boolean = false
    ) : RssEvent

    data class FetchRssFeeds(
        val onCurrentStep: ((Int) -> Unit)? = null,
        val onTotalSteps: ((Int) -> Unit),
        val onDone: () -> Unit
    ) : RssEvent
}
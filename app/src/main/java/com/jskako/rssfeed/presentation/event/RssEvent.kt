package com.jskako.rssfeed.presentation.event

sealed interface RssEvent {
    data class HasBeenRead(val guid: String, val hasBeenRead: Boolean = true) : RssEvent
    data class SelectChannel(val rss: String) : RssEvent
    data class DeleteChannel(val rss: String) : RssEvent
    data class UpdateNotification(val rss: String, val isEnabled: Boolean) : RssEvent
    data class UpdateFavorite(val guid: String, val isFavorite: Boolean) : RssEvent
    data class FetchRssFeed(
        val rss: String,
        val runRssExistCheck: Boolean = true,
        val setSelected: Boolean = false
    ) : RssEvent

    data class FetchRssFeeds(val onDone: () -> Unit) : RssEvent

    data class ScheduleWork(val rss: String) : RssEvent
    data class CancelWork(val rss: String) : RssEvent
}
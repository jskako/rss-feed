package com.jskako.rssfeed.presentation.delegate

import com.jskako.rssfeed.domain.model.database.RssChannel
import com.jskako.rssfeed.domain.model.database.RssItem
import java.time.Instant

interface DatabaseDelegate {
    suspend fun updateNotification(rss: String, isEnabled: Boolean)
    suspend fun isChannelUpdated(rss: String, currentDate: Instant?): Boolean
    suspend fun addChannelToDatabase(rssChannel: RssChannel)
    suspend fun addItemsToDatabase(rssItems: List<RssItem>)
    suspend fun channelExist(rss: String): Boolean
}
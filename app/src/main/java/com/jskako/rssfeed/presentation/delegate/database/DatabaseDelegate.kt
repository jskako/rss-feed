package com.jskako.rssfeed.presentation.delegate.database

import com.jskako.rssfeed.domain.model.database.RssChannel
import com.jskako.rssfeed.domain.model.database.RssItem
import kotlinx.coroutines.flow.Flow
import java.time.Instant

interface DatabaseDelegate {
    fun getRssChannels(): Flow<List<RssChannel>>
    fun getRssItems(rss: String): Flow<List<RssItem>>
    suspend fun deleteRssChannel(rss: String)
    suspend fun isNotificationEnabled(rss: String): Boolean
    suspend fun isFavorite(guid: String): Boolean
    suspend fun hasBeenRead(guid: String): Boolean
    suspend fun updateNotification(rss: String, isEnabled: Boolean)
    suspend fun isChannelUpdated(rss: String, lastBuildDate: Instant?): Boolean
    suspend fun channelExist(rss: String): Boolean
    suspend fun addToDatabase(
        rss: String,
        rssChannel: RssChannel,
        rssItems: List<RssItem>,
    )
}
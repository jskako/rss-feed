package com.jskako.rssfeed.domain.repository

import com.jskako.rssfeed.domain.model.database.RssChannel
import kotlinx.coroutines.flow.Flow
import java.time.Instant

interface RssChannelRepository {
    suspend fun insertRss(rssChannel: RssChannel)
    fun getAllRss(): Flow<List<RssChannel>>
    suspend fun deleteRssByUrl(rss: String)
    suspend fun get(rss: String): RssChannel?
    suspend fun getLastBuildDate(rss: String): Instant?
    suspend fun channelExists(rss: String): Boolean
    suspend fun isNotificationEnabled(rss: String): Boolean
    suspend fun updateNotification(rss: String, isEnabled: Boolean)
}
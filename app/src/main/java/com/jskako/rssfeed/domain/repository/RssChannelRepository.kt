package com.jskako.rssfeed.domain.repository

import com.jskako.rssfeed.domain.model.database.RssChannel
import kotlinx.coroutines.flow.Flow
import java.time.Instant

interface RssChannelRepository {
    suspend fun insertRss(rssChannel: RssChannel)
    fun getAllRss(): Flow<List<RssChannel>>
    suspend fun deleteRssByUrl(url: String)
    suspend fun get(url: String): RssChannel?
    suspend fun getLastBuildDate(url: String): Instant?
    suspend fun channelExists(rss: String): Boolean
}
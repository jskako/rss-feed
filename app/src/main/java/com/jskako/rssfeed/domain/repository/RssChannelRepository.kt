package com.jskako.rssfeed.domain.repository

import com.jskako.rssfeed.domain.model.RssChannel
import kotlinx.coroutines.flow.Flow
import java.time.Instant

interface RssChannelRepository {
    suspend fun insertRss(rssChannel: RssChannel)
    fun getAllRss(): Flow<List<RssChannel>>
    suspend fun deleteRssByUrl(url: String)
    suspend fun get(url: String): RssChannel?
    suspend fun getLastBuildDate(url: String): Instant?
}
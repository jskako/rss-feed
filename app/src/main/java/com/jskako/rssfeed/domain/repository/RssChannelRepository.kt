package com.jskako.rssfeed.domain.repository

import com.jskako.rssfeed.domain.model.RssChannel
import kotlinx.coroutines.flow.Flow

interface RssChannelRepository {
    suspend fun insertRss(rssChannel: RssChannel)
    fun getAllRss(): Flow<List<RssChannel>>
    suspend fun deleteRssByUrl(url: String)
}
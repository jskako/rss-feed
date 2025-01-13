package com.jskako.rssfeed.domain.repository

import com.jskako.rssfeed.domain.model.RssFeed
import kotlinx.coroutines.flow.Flow

interface RssFeedRepository {
    suspend fun insertRss(rssFeed: RssFeed)
    fun getAllRss(): Flow<List<RssFeed>>
    suspend fun deleteRssByUrl(url: String)
}
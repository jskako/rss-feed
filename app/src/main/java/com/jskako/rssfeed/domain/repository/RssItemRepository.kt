package com.jskako.rssfeed.domain.repository

import com.jskako.rssfeed.domain.model.database.RssItem
import kotlinx.coroutines.flow.Flow
import java.time.Instant

interface RssItemRepository {
    suspend fun insert(rssItem: RssItem)
    fun getItems(rss: String): Flow<List<RssItem>>
    suspend fun get(guid: String): RssItem?
    suspend fun getLastUpdateDate(guid: String): Instant?
    suspend fun itemExists(guid: String): Boolean
    fun unreadItems(rss: String): Flow<Int>
    suspend fun updateReadStatus(guid: String, hasBeenRead: Boolean)
    suspend fun isFavorite(guid: String): Boolean
    suspend fun hasBeenRead(guid: String): Boolean
    suspend fun updateFavorite(guid: String, isFavorite: Boolean)
}
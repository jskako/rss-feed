package com.jskako.rssfeed.data.local.repository

import com.jskako.rssfeed.core.utils.toInstantOrNull
import com.jskako.rssfeed.data.local.dao.RssItemDao
import com.jskako.rssfeed.data.local.mapper.toRssItem
import com.jskako.rssfeed.data.local.mapper.toRssItemEntity
import com.jskako.rssfeed.domain.model.database.RssItem
import com.jskako.rssfeed.domain.repository.RssItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant

class RssItemIRepositoryImpl(
    private val rssItemDao: RssItemDao
) : RssItemRepository {
    override suspend fun insert(rssItem: RssItem) {
        rssItemDao.upsert(rssItem.toRssItemEntity())
    }

    override fun getItems(rss: String): Flow<List<RssItem>> {
        return rssItemDao.getItemsByRss(rss).map { entities ->
            entities.map { it.toRssItem() }
        }
    }

    override suspend fun deleteByRss(rss: String) {
        rssItemDao.deleteByRss(rss)
    }

    override suspend fun deleteByGuid(guid: String) {
        rssItemDao.deleteByGuid(guid)
    }

    override suspend fun get(guid: String): RssItem? {
        return rssItemDao.getByGuid(guid)?.toRssItem()
    }

    override suspend fun getLastUpdateDate(url: String): Instant? {
        return rssItemDao.getLastUpdateDate(url)?.toInstantOrNull()
    }

    override suspend fun itemExists(guid: String): Boolean {
        return rssItemDao.itemExists(guid)
    }

    override suspend fun unreadItems(rss: String): Int {
        return rssItemDao.countUnreadItems(rss)
    }

    override suspend fun updateReadStatus(guid: String, isEnabled: Boolean) {
        rssItemDao.updateReadStatus(guid, isEnabled)
    }

    override suspend fun isFavorite(guid: String): Boolean {
        return rssItemDao.isFavorite(guid)
    }

    override suspend fun updateFavorite(guid: String, isEnabled: Boolean) {
        rssItemDao.updateFavorite(guid, isEnabled)
    }
}
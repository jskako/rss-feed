package com.jskako.rssfeed.data.local.repository

import com.jskako.rssfeed.data.local.dao.RssEntityDao
import com.jskako.rssfeed.data.local.mapper.toRssEntity
import com.jskako.rssfeed.data.local.mapper.toRssItem
import com.jskako.rssfeed.domain.model.RssFeed
import com.jskako.rssfeed.domain.repository.RssRepository
import kotlinx.coroutines.flow.map

class RssEntityRepositoryImpl(
    private val rssEntityDao: RssEntityDao
) : RssRepository {

    override suspend fun insertRss(rssFeed: RssFeed) =
        rssEntityDao.insertRss(rssFeed.toRssEntity())

    override fun getAllRss() = rssEntityDao.getAll().map { entities ->
        entities.map { it.toRssItem() }
    }

    override suspend fun deleteRssByUrl(url: String) {
        rssEntityDao.deleteByUrl(url)
    }
}
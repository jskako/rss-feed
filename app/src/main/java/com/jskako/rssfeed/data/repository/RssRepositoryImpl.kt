package com.jskako.rssfeed.data.repository

import com.jskako.rssfeed.data.local.dao.RssDao
import com.jskako.rssfeed.data.mapper.toRssEntity
import com.jskako.rssfeed.data.mapper.toRssItem
import com.jskako.rssfeed.domain.model.RssFeed
import com.jskako.rssfeed.domain.repository.RssRepository
import kotlinx.coroutines.flow.map

class RssRepositoryImpl(
    private val rssDao: RssDao
) : RssRepository {

    override suspend fun insertRss(rssFeed: RssFeed) =
        rssDao.insertRss(rssFeed.toRssEntity())

    override fun getAllRss() = rssDao.getAll().map { entities ->
        entities.map { it.toRssItem() }
    }

    override suspend fun deleteRssByUrl(url: String) {
        rssDao.deleteByUrl(url)
    }
}
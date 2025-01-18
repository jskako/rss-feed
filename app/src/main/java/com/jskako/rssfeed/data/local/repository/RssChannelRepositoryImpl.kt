package com.jskako.rssfeed.data.local.repository

import com.jskako.rssfeed.core.utils.toInstantOrNull
import com.jskako.rssfeed.data.local.dao.RssChannelDao
import com.jskako.rssfeed.data.local.mapper.toRssChannel
import com.jskako.rssfeed.data.local.mapper.toRssChannelEntity
import com.jskako.rssfeed.domain.model.database.RssChannel
import com.jskako.rssfeed.domain.repository.RssChannelRepository
import kotlinx.coroutines.flow.map
import java.time.Instant

class RssChannelRepositoryImpl(
    private val rssChannelDao: RssChannelDao
) : RssChannelRepository {

    override suspend fun insertRss(rssChannel: RssChannel) =
        rssChannelDao.upsertRss(rssChannel.toRssChannelEntity())

    override fun getAllRss() = rssChannelDao.getAll().map { entities ->
        entities.map { it.toRssChannel() }
    }

    override suspend fun deleteRssByUrl(url: String) {
        rssChannelDao.deleteByUrl(url)
    }

    override suspend fun get(url: String): RssChannel? {
        return rssChannelDao.get(url)?.toRssChannel()
    }

    override suspend fun getLastBuildDate(url: String): Instant? {
        return rssChannelDao.getLastBuildDate(url)?.toInstantOrNull()
    }

    override suspend fun channelExists(rss: String): Boolean {
        return rssChannelDao.channelExist(rss)
    }

    override suspend fun isNotificationEnabled(rss: String): Boolean {
        return rssChannelDao.isNotificationEnabled(rss)
    }

    override suspend fun updateNotification(rss: String, isEnabled: Boolean) {
        rssChannelDao.updateNotification(rss, isEnabled)
    }
}
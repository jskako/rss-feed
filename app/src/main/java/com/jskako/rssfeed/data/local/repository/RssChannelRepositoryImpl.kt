package com.jskako.rssfeed.data.local.repository

import com.jskako.rssfeed.data.local.dao.RssChanelEntityDao
import com.jskako.rssfeed.data.local.mapper.toRssChannel
import com.jskako.rssfeed.data.local.mapper.toRssInfoEntity
import com.jskako.rssfeed.domain.model.channel.RssChannel
import com.jskako.rssfeed.domain.repository.channel.RssChannelRepository
import kotlinx.coroutines.flow.map
import java.time.Instant

class RssChannelRepositoryImpl(
    private val rssChanelEntityDao: RssChanelEntityDao
) : RssChannelRepository {

    override suspend fun insertRss(rssChannel: RssChannel) =
        rssChanelEntityDao.upsertRss(rssChannel.toRssInfoEntity())

    override fun getAllRss() = rssChanelEntityDao.getAll().map { entities ->
        entities.map { it.toRssChannel() }
    }

    override suspend fun deleteRssByUrl(url: String) {
        rssChanelEntityDao.deleteByUrl(url)
    }

    override suspend fun get(url: String): RssChannel? {
        return rssChanelEntityDao.get(url)?.toRssChannel()
    }

    override suspend fun getLastBuildDate(url: String): Instant? {
        return rssChanelEntityDao.getLastBuildDate(url)?.let { Instant.parse(it) }
    }

    override suspend fun channelExists(rss: String): Boolean {
        return rssChanelEntityDao.channelExist(rss)
    }
}
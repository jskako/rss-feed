package com.jskako.rssfeed.data.local.repository

import com.jskako.rssfeed.data.local.dao.RssChanelEntityDao
import com.jskako.rssfeed.data.local.mapper.toRssChannel
import com.jskako.rssfeed.data.local.mapper.toRssEntity
import com.jskako.rssfeed.domain.model.RssChannel
import com.jskako.rssfeed.domain.repository.RssChannelRepository
import kotlinx.coroutines.flow.map

class RssChannelRepositoryImpl(
    private val rssChanelEntityDao: RssChanelEntityDao
) : RssChannelRepository {

    override suspend fun insertRss(rssChannel: RssChannel) =
        rssChanelEntityDao.insertRss(rssChannel.toRssEntity())

    override fun getAllRss() = rssChanelEntityDao.getAll().map { entities ->
        entities.map { it.toRssChannel() }
    }

    override suspend fun deleteRssByUrl(url: String) {
        rssChanelEntityDao.deleteByUrl(url)
    }
}
package com.jskako.rssfeed.data.local.repository

import com.jskako.rssfeed.data.local.dao.RssChanelInfoEntityDao
import com.jskako.rssfeed.data.local.mapper.toRssInfoEntity
import com.jskako.rssfeed.domain.model.channel.RssChannelInfo
import com.jskako.rssfeed.domain.repository.channel.RssChannelInfoRepository

class RssChannelInfoRepositoryImpl(
    private val rssChanelInfoEntityDao: RssChanelInfoEntityDao
) : RssChannelInfoRepository {

    override suspend fun upsertRssInfo(rssChannelInfo: RssChannelInfo) {
        rssChanelInfoEntityDao.upsertRssChannelInfo(rssChannelInfo.toRssInfoEntity())
    }

    override suspend fun isNotificationEnabled(rss: String): Boolean {
        return rssChanelInfoEntityDao.isNotificationEnabled(rss)
    }
}
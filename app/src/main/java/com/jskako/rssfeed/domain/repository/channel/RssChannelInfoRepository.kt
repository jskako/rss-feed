package com.jskako.rssfeed.domain.repository.channel

import com.jskako.rssfeed.domain.model.channel.RssChannelInfo

interface RssChannelInfoRepository {
    suspend fun upsertRssInfo(rssChannelInfo: RssChannelInfo)
    suspend fun isNotificationEnabled(rss: String): Boolean
}
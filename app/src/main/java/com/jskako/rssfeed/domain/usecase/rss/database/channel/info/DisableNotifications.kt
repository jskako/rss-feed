package com.jskako.rssfeed.domain.usecase.rss.database.channel.info

import com.jskako.rssfeed.domain.model.channel.RssChannelInfo
import com.jskako.rssfeed.domain.repository.channel.RssChannelInfoRepository

class DisableNotifications(private val rssRepository: RssChannelInfoRepository) {
    suspend operator fun invoke(rssKey: String) {
        val rssChannelInfo = RssChannelInfo(rss = rssKey, notifications = false)
        rssRepository.upsertRssInfo(rssChannelInfo)
    }
}
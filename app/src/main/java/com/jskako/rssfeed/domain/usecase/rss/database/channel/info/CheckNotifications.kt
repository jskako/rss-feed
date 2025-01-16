package com.jskako.rssfeed.domain.usecase.rss.database.channel.info

import com.jskako.rssfeed.domain.repository.channel.RssChannelInfoRepository

class CheckNotifications(private val rssRepository: RssChannelInfoRepository) {
    suspend operator fun invoke(rssKey: String): Boolean {
        return rssRepository.isNotificationEnabled(rssKey)
    }
}
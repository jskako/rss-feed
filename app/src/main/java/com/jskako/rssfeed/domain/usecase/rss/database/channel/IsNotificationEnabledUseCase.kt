package com.jskako.rssfeed.domain.usecase.rss.database.channel

import com.jskako.rssfeed.domain.repository.RssChannelRepository

class IsNotificationEnabledUseCase(
    private val repository: RssChannelRepository
) {
    suspend operator fun invoke(rss: String): Boolean {
        return repository.isNotificationEnabled(rss)
    }
}
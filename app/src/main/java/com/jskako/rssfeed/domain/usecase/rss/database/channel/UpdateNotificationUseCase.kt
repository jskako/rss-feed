package com.jskako.rssfeed.domain.usecase.rss.database.channel

import com.jskako.rssfeed.domain.repository.RssChannelRepository

class UpdateNotificationUseCase(
    private val repository: RssChannelRepository
) {
    suspend operator fun invoke(rss: String, isEnabled: Boolean) {
        repository.updateNotification(rss, isEnabled)
    }
}
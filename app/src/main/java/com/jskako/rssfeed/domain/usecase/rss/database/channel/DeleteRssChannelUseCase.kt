package com.jskako.rssfeed.domain.usecase.rss.database.channel

import com.jskako.rssfeed.domain.repository.RssChannelRepository

class DeleteRssChannelUseCase(
    private val repository: RssChannelRepository
) {
    suspend operator fun invoke(url: String) {
        repository.deleteRssByUrl(url)
    }
}
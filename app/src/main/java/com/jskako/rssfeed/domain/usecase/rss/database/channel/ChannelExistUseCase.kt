package com.jskako.rssfeed.domain.usecase.rss.database.channel

import com.jskako.rssfeed.domain.repository.RssChannelRepository

class ChannelExistUseCase(
    private val repository: RssChannelRepository
) {
    suspend operator fun invoke(rss: String) = repository.channelExists(rss = rss)
}
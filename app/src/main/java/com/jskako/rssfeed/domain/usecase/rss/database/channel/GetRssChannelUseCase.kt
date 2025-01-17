package com.jskako.rssfeed.domain.usecase.rss.database.channel

import com.jskako.rssfeed.domain.model.database.RssChannel
import com.jskako.rssfeed.domain.repository.RssChannelRepository

class GetRssChannelUseCase(
    private val repository: RssChannelRepository
) {
    suspend operator fun invoke(url: String): RssChannel? {
        return repository.get(url)
    }
}

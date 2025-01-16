package com.jskako.rssfeed.domain.usecase.rss.database.channel

import com.jskako.rssfeed.domain.repository.channel.RssChannelRepository
import java.time.Instant

class GetLastBuildDateUseCase(
    private val repository: RssChannelRepository
) {
    suspend operator fun invoke(url: String): Instant? {
        return repository.getLastBuildDate(url)
    }
}
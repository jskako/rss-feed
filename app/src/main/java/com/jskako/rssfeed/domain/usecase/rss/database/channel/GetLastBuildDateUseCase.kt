package com.jskako.rssfeed.domain.usecase.rss.database.channel

import com.jskako.rssfeed.domain.repository.RssChannelRepository
import java.time.Instant

class GetLastBuildDateUseCase(
    private val repository: RssChannelRepository
) {
    suspend operator fun invoke(rss: String): Instant? {
        return repository.getLastBuildDate(rss)
    }
}
package com.jskako.rssfeed.domain.usecase.rss.database

import com.jskako.rssfeed.domain.repository.RssChannelRepository

class GetLastBuildDateUseCase(
    private val repository: RssChannelRepository
) {
    suspend operator fun invoke(url: String): String? {
        return repository.getLastBuildDate(url)
    }
}
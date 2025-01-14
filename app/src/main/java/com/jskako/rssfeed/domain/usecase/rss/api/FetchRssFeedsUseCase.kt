package com.jskako.rssfeed.domain.usecase.rss.api

import com.jskako.rssfeed.data.remote.models.RssResponseDto
import com.jskako.rssfeed.domain.repository.RssApiRepository

class FetchRssFeedUseCase(private val rssRepository: RssApiRepository) {
    suspend operator fun invoke(link: String): RssResponseDto? {
        return rssRepository.getRssFeed(link)
    }
}
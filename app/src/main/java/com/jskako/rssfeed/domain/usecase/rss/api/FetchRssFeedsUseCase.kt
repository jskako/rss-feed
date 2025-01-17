package com.jskako.rssfeed.domain.usecase.rss.api

import com.jskako.rssfeed.domain.model.api.RssApiResponse
import com.jskako.rssfeed.domain.repository.RssApiRepository

class FetchRssFeedUseCase(private val rssRepository: RssApiRepository) {
    suspend operator fun invoke(link: String): RssApiResponse? {
        return rssRepository.getRssFeed(link)
    }
}
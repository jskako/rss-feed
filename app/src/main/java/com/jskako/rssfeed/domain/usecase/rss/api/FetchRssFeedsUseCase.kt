package com.jskako.rssfeed.domain.usecase.rss.api

import com.jskako.rssfeed.domain.model.RssResponse
import com.jskako.rssfeed.domain.repository.RssApiRepository

class FetchRssFeedUseCase(private val rssRepository: RssApiRepository) {
    suspend operator fun invoke(link: String): RssResponse? {
        return rssRepository.getRssFeed(link)
    }
}
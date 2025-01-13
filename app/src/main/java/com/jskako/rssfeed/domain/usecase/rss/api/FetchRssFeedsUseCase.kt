package com.jskako.rssfeed.domain.usecase.rss.api

import com.jskako.rssfeed.domain.model.RssFeed
import com.jskako.rssfeed.domain.repository.RssApiRepository

class FetchRssFeedUseCase(private val rssRepository: RssApiRepository) {
    suspend operator fun invoke(link: String): List<RssFeed> {
        return rssRepository.getRssFeed(link)
    }
}
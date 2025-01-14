package com.jskako.rssfeed.domain.usecase.rss.api

import com.jskako.rssfeed.domain.repository.RssApiRepository

class CheckUrlReachabilityUseCase(private val rssRepository: RssApiRepository) {
    suspend operator fun invoke(link: String): Boolean {
        return rssRepository.isUrlReachable(link)
    }
}
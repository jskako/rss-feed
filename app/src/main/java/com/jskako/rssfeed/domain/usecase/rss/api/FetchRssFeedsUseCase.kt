package com.jskako.rssfeed.domain.usecase.rss.api

import com.jskako.rssfeed.domain.model.api.RssApiResponse
import com.jskako.rssfeed.domain.repository.RssApiRepository
import com.jskako.rssfeed.presentation.delegate.database.DatabaseDelegate

class FetchRssFeedUseCase(
    private val rssRepository: RssApiRepository,
    private val databaseDelegate: DatabaseDelegate
) {
    suspend operator fun invoke(
        rss: String,
        runRssExistCheck: Boolean = false
    ): RssApiResponse {
        if (runRssExistCheck && databaseDelegate.channelExist(rss)) {
            throw IllegalArgumentException("RSS already added: $rss")
        }

        if (!rssRepository.isUrlReachable(rss)) {
            throw IllegalArgumentException("The RSS link is not reachable: $rss")
        }

        return rssRepository.fetchRssFeeds(rss = rss)
            ?: throw Exception("Failed to fetch feeds for $rss")
    }
}
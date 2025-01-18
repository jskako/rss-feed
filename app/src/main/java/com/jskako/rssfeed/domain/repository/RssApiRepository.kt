package com.jskako.rssfeed.domain.repository

import com.jskako.rssfeed.domain.model.api.RssApiResponse

interface RssApiRepository {
    suspend fun getRssFeed(rss: String): RssApiResponse?
    suspend fun isUrlReachable(rss: String): Boolean
}
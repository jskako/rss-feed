package com.jskako.rssfeed.domain.repository

import com.jskako.rssfeed.domain.model.api.RssApiResponse

interface RssApiRepository {
    suspend fun getRssFeed(link: String): RssApiResponse?
    suspend fun isUrlReachable(link: String): Boolean
}
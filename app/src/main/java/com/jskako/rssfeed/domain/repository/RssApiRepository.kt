package com.jskako.rssfeed.domain.repository

import com.jskako.rssfeed.domain.model.RssResponse

interface RssApiRepository {
    suspend fun getRssFeed(link: String): RssResponse?
    suspend fun isUrlReachable(link: String): Boolean
}
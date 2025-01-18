package com.jskako.rssfeed.data.remote.repository

import com.jskako.rssfeed.data.remote.api.RssApi
import com.jskako.rssfeed.domain.model.api.RssApiResponse
import com.jskako.rssfeed.domain.repository.RssApiRepository

class RssApiRepositoryImpl(
    private val rssApi: RssApi
) : RssApiRepository {

    override suspend fun getRssFeed(rss: String): RssApiResponse? {
        return rssApi.fetchRss(rss)
    }

    override suspend fun isUrlReachable(rss: String): Boolean {
        return rssApi.isUrlReachable(rss)
    }
}
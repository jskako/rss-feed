package com.jskako.rssfeed.data.remote.repository

import com.jskako.rssfeed.data.remote.api.RssApi
import com.jskako.rssfeed.domain.model.api.RssApiResponse
import com.jskako.rssfeed.domain.repository.RssApiRepository

class RssApiRepositoryImpl(
    private val rssApi: RssApi
) : RssApiRepository {

    override suspend fun getRssFeed(link: String): RssApiResponse? {
        return rssApi.fetchRss(link)
    }

    override suspend fun isUrlReachable(link: String): Boolean {
        return rssApi.isUrlReachable(link)
    }
}
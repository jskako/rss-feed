package com.jskako.rssfeed.data.remote.repository

import com.jskako.rssfeed.data.remote.api.RssApi
import com.jskako.rssfeed.domain.model.RssResponse
import com.jskako.rssfeed.domain.repository.RssApiRepository

class RssApiRepositoryImpl(
    private val rssApi: RssApi
) : RssApiRepository {

    override suspend fun getRssFeed(link: String): RssResponse? {
        return rssApi.fetchRss(link)
    }
}
package com.jskako.rssfeed.domain.repository

import com.jskako.rssfeed.domain.model.RssFeed

interface RssApiRepository {
    suspend fun getRssFeed(link: String): List<RssFeed>
}
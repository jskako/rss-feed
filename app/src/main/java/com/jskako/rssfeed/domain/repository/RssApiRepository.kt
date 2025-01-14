package com.jskako.rssfeed.domain.repository

import com.jskako.rssfeed.data.remote.models.RssResponseDto

interface RssApiRepository {
    suspend fun getRssFeed(link: String): RssResponseDto?
}
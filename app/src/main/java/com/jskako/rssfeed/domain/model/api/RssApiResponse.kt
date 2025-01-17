package com.jskako.rssfeed.domain.model.api

data class RssApiResponse(
    val rssApiChannel: RssApiChannel,
    val rssApiItems: List<RssApiItem>
)
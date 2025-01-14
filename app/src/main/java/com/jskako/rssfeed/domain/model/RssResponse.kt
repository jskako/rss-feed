package com.jskako.rssfeed.domain.model

data class RssResponse(
    val rssChannel: RssChannel,
    val rssItem: List<RssItem>
)
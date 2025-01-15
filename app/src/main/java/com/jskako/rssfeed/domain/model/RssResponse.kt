package com.jskako.rssfeed.domain.model

data class RssResponse(
    val rssChannel: RssChannel,
    val rssItems: List<RssItem>
)
package com.jskako.rssfeed.domain.model.database

data class RssResponse(
    val rssChannel: RssChannel,
    val rssItems: List<RssItem>
)
package com.jskako.rssfeed.domain.model

import com.jskako.rssfeed.domain.model.channel.RssChannel
import com.jskako.rssfeed.domain.model.item.RssItem

data class RssResponse(
    val rssChannel: RssChannel,
    val rssItems: List<RssItem>
)
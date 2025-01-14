package com.jskako.rssfeed.data.remote.mapper

import com.jskako.rssfeed.data.remote.models.RssChannelDto
import com.jskako.rssfeed.data.remote.models.RssItemDto
import com.jskako.rssfeed.data.remote.models.RssResponseDto
import com.jskako.rssfeed.domain.model.RssChannel
import com.jskako.rssfeed.domain.model.RssItem
import com.jskako.rssfeed.domain.model.RssResponse
import java.time.Instant

fun RssResponseDto.toRssResponse(rss: String) = RssResponse(
    rssChannel = this.channel.toRssChannel(rss = rss),
    rssItem = this.channel.item.toRssItems(rss = rss)
)

fun RssChannelDto.toRssChannel(rss: String): RssChannel = RssChannel(
    rss = rss,
    title = this.title,
    link = this.link,
    description = this.description,
    lastBuildDate = this.lastBuildDate?.let { Instant.parse(it) },
    imagePath = this.image?.url
)

fun List<RssItemDto>.toRssItems(rss: String): List<RssItem> = this.map { itemDto ->
    RssItem(
        guid = itemDto.guid,
        rss = rss,
        title = itemDto.title,
        link = itemDto.link,
        updateDate = itemDto.updateDate?.let { Instant.parse(it) },
        imagePaths = emptyList()
    )
}
package com.jskako.rssfeed.data.remote.mapper

import com.jskako.rssfeed.core.utils.toInstantOrNull
import com.jskako.rssfeed.data.remote.models.RssChannelDto
import com.jskako.rssfeed.data.remote.models.RssItemDto
import com.jskako.rssfeed.data.remote.models.RssResponseDto
import com.jskako.rssfeed.domain.model.api.RssApiChannel
import com.jskako.rssfeed.domain.model.api.RssApiItem
import com.jskako.rssfeed.domain.model.api.RssApiResponse

fun RssResponseDto.toRssApiResponse(rss: String) = RssApiResponse(
    rssApiChannel = this.channel.toRssApiChannel(rss = rss),
    rssApiItems = this.channel.item.toRssApiItems(rss = rss)
)

private fun RssChannelDto.toRssApiChannel(rss: String): RssApiChannel = RssApiChannel(
    rss = rss,
    title = this.title,
    link = this.link,
    description = this.description,
    lastBuildDate = this.lastBuildDate?.toInstantOrNull(),
    imagePath = this.image?.url
)

private fun List<RssItemDto>.toRssApiItems(rss: String): List<RssApiItem> = this.map { itemDto ->
    RssApiItem(
        guid = itemDto.guid,
        rss = rss,
        title = itemDto.title,
        link = itemDto.link,
        updateDate = itemDto.updateDate?.toInstantOrNull(),
        expiresDate = itemDto.expiresDate?.toInstantOrNull(),
        imagePaths = itemDto.content?.mapNotNull { it.url },
        description = itemDto.description
    )
}
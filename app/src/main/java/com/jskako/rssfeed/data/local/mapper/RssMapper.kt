package com.jskako.rssfeed.data.local.mapper

import com.jskako.rssfeed.data.local.models.RssEntity
import com.jskako.rssfeed.domain.model.RssChannel

fun RssEntity.toRssItem(): RssChannel {
    return RssChannel(
        rss = this.rss,
        title = this.title,
        description = this.description,
        link = this.link,
        lastBuildDate = null,//this.lastBuildDate,
        imagePath = this.imagePath
    )
}

fun RssChannel.toRssEntity(): RssEntity {
    return RssEntity(
        rss = this.rss,
        title = this.title,
        description = this.description,
        link = this.link,
        lastBuildDate = null,//this.lastBuildDate,
        imagePath = this.imagePath
    )
}
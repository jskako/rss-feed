package com.jskako.rssfeed.data.mapper

import com.jskako.rssfeed.data.model.RssEntity
import com.jskako.rssfeed.domain.model.RssFeed

fun RssEntity.toRssItem(): RssFeed {
    return RssFeed(
        rss = this.rss,
        title = this.title,
        description = this.description,
        link = this.link,
        lastBuildDate = this.lastBuildDate,
        imagePath = this.imagePath
    )
}

fun RssFeed.toRssEntity(): RssEntity {
    return RssEntity(
        rss = this.rss,
        title = this.title,
        description = this.description,
        link = this.link,
        lastBuildDate = this.lastBuildDate,
        imagePath = this.imagePath
    )
}
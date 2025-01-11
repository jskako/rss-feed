package com.jskako.rssfeed.data.mapper

import com.jskako.rssfeed.core.utils.toBitmap
import com.jskako.rssfeed.core.utils.toByteArray
import com.jskako.rssfeed.data.model.RssEntity
import com.jskako.rssfeed.domain.model.RssFeed

fun RssEntity.toRssItem(): RssFeed {
    return RssFeed(
        url = this.url,
        description = this.description,
        image = this.image?.toBitmap()
    )
}

fun RssFeed.toRssEntity(): RssEntity {
    return RssEntity(
        url = this.url,
        description = this.description,
        image = this.image?.toByteArray()
    )
}
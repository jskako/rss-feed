package com.jskako.rssfeed.data.local.mapper

import com.jskako.rssfeed.core.utils.toFormattedString
import com.jskako.rssfeed.core.utils.toInstantOrNull
import com.jskako.rssfeed.data.local.models.RssItemEntity
import com.jskako.rssfeed.domain.model.database.RssItem

fun RssItemEntity.toRssItem(): RssItem {
    return RssItem(
        guid = this.guid,
        rss = this.rss,
        title = this.title,
        updateDate = this.updateDate?.toInstantOrNull(),
        expiresDate = this.expiresDate?.toInstantOrNull(),
        link = this.link,
        description = this.description,
        imagePaths = this.imagePaths,
        hasBeenRead = this.hasBeenRead,
        isFavorite = this.isFavorite,
    )
}

fun RssItem.toRssItemEntity(): RssItemEntity {
    return RssItemEntity(
        guid = this.guid,
        rss = this.rss,
        title = this.title,
        updateDate = this.updateDate?.toFormattedString(),
        expiresDate = this.expiresDate?.toFormattedString(),
        link = this.link,
        description = this.description,
        imagePaths = this.imagePaths,
        hasBeenRead = this.hasBeenRead,
        isFavorite = this.isFavorite,
    )
}
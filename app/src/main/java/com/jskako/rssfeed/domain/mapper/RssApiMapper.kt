package com.jskako.rssfeed.domain.mapper

import com.jskako.rssfeed.domain.model.api.RssApiChannel
import com.jskako.rssfeed.domain.model.api.RssApiItem
import com.jskako.rssfeed.domain.model.database.RssChannel
import com.jskako.rssfeed.domain.model.database.RssItem

fun RssApiChannel.toRssChannel(isNotificationEnabled: Boolean): RssChannel {
    return RssChannel(
        rss = this.rss,
        title = this.title,
        link = this.link,
        description = this.description,
        lastBuildDate = this.lastBuildDate,
        imagePath = this.imagePath,
        notifications = isNotificationEnabled
    )
}

fun RssApiItem.toRssItem(
    hasBeenRead: Boolean,
    isFavorite: Boolean
): RssItem {
    return RssItem(
        guid = this.guid,
        rss = this.rss,
        title = this.title,
        updateDate = this.updateDate,
        expiresDate = this.expiresDate,
        link = this.link,
        description = this.description,
        imagePaths = this.imagePaths,
        hasBeenRead = hasBeenRead,
        isFavorite = isFavorite
    )
}
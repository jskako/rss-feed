package com.jskako.rssfeed.domain.mapper

import com.jskako.rssfeed.domain.model.api.RssApiChannel
import com.jskako.rssfeed.domain.model.api.RssApiItem
import com.jskako.rssfeed.domain.model.database.RssChannel
import com.jskako.rssfeed.domain.model.database.RssItem
import com.jskako.rssfeed.domain.usecase.rss.database.DatabaseChannelUseCases

suspend fun RssApiChannel.toRssChannel(databaseChannelUseCases: DatabaseChannelUseCases): RssChannel {
    return RssChannel(
        rss = this.rss,
        title = this.title,
        link = this.link,
        description = this.description,
        lastBuildDate = this.lastBuildDate,
        imagePath = this.imagePath,
        notifications = databaseChannelUseCases.isNotificationEnabled(rss = this.rss)
    )
}

suspend fun List<RssApiItem>.toRssItems(databaseChannelUseCases: DatabaseChannelUseCases): List<RssItem> {
    return this.map { it.toRssItem(databaseChannelUseCases) }
}

suspend fun RssApiItem.toRssItem(databaseChannelUseCases: DatabaseChannelUseCases): RssItem {
    return RssItem(
        guid = this.guid,
        rss = this.rss,
        title = this.rss,
        updateDate = this.updateDate,
        expiresDate = this.expiresDate,
        link = this.link,
        description = this.description,
        imagePaths = this.imagePaths,
        hasBeenRead = false,
        isFavorite = false
    )
}
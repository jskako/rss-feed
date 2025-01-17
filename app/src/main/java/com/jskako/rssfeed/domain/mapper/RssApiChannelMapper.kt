package com.jskako.rssfeed.domain.mapper

import com.jskako.rssfeed.domain.model.api.RssApiChannel
import com.jskako.rssfeed.domain.model.database.RssChannel
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
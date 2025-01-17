package com.jskako.rssfeed.data.local.mapper

import com.jskako.rssfeed.core.utils.toFormattedString
import com.jskako.rssfeed.data.local.models.RssChannelEntity
import com.jskako.rssfeed.domain.model.database.RssChannel
import java.time.Instant

fun RssChannelEntity.toRssChannel(): RssChannel {
    return RssChannel(
        rss = this.rss,
        title = this.title,
        description = this.description,
        link = this.link,
        lastBuildDate = this.lastBuildDate?.let { Instant.parse(it) },
        imagePath = this.imagePath,
        notifications = this.isNotificationEnabled
    )
}

fun RssChannel.toRssInfoEntity(): RssChannelEntity {
    return RssChannelEntity(
        rss = this.rss,
        title = this.title,
        description = this.description,
        link = this.link,
        lastBuildDate = this.lastBuildDate?.toFormattedString(),
        imagePath = this.imagePath,
        isNotificationEnabled = this.notifications
    )
}
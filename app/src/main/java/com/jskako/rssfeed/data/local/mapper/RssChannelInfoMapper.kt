package com.jskako.rssfeed.data.local.mapper

import com.jskako.rssfeed.data.local.models.RssChannelInfoEntity
import com.jskako.rssfeed.domain.model.channel.RssChannelInfo

fun RssChannelInfoEntity.toRssChannelInfo(): RssChannelInfo {
    return RssChannelInfo(
        rss = this.rss,
        notifications = this.isNotificationEnabled
    )
}

fun RssChannelInfo.toRssInfoEntity(): RssChannelInfoEntity {
    return RssChannelInfoEntity(
        rss = this.rss,
        isNotificationEnabled = this.notifications
    )
}
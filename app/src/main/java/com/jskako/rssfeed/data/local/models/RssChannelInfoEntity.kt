package com.jskako.rssfeed.data.local.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "rss_channel_info",
    foreignKeys = [
        ForeignKey(
            entity = RssChannelEntity::class,
            parentColumns = [RSS_KEY],
            childColumns = [RSS_KEY],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = [RSS_KEY])]
)
data class RssChannelInfoEntity(
    @PrimaryKey val rss: String,
    val onWatchList: Boolean = false,
    val customTitle: String = ""
)

private const val RSS_KEY = "rss"
package com.jskako.rssfeed.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rss_channel")
data class RssChannelEntity(
    @PrimaryKey val rss: String,
    val title: String?,
    val description: String?,
    val link: String?,
    @ColumnInfo(name = "last_build_date") val lastBuildDate: String?,
    @ColumnInfo(name = "image_path") val imagePath: String?,
    val isNotificationEnabled: Boolean = false
)
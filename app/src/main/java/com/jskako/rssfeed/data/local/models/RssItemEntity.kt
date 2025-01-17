package com.jskako.rssfeed.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "rss_item",
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
data class RssItemEntity(
    @PrimaryKey val guid: String,
    val rss: String,
    val title: String?,
    @ColumnInfo(name = "update_date") val updateDate: String,
    @ColumnInfo(name = "expires_date") val expiresDate: String?,
    val list: String,
    val description: String,
    @ColumnInfo(name = "image_paths") val imagePaths: List<String>,
    val hasBeenRead: Boolean,
    val isFavorite: Boolean = false
)

private const val RSS_KEY = "rss"
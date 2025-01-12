package com.jskako.rssfeed.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "rss_item_entity",
    foreignKeys = [
        ForeignKey(
            entity = RssEntity::class,
            parentColumns = [RSS_NAME],
            childColumns = [RSS_NAME],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = [RSS_NAME])]
)
data class RssItemEntity(
    @PrimaryKey val guid: String,
    val rss: String,                            // Foreign key referencing RssEntity's rss column
    val title: String?,
    @ColumnInfo(name = "update_date") val updateDate: String?,
    @ColumnInfo(name = "expires_date") val expiresDate: String?,
    val list: String,
    val description: String,
    @ColumnInfo(name = "image_paths") val imagePaths: List<String>,
    val hasBeenRead: Boolean
)

private const val RSS_NAME = "rss"
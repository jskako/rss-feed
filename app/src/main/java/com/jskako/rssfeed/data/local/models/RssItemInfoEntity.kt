package com.jskako.rssfeed.data.local.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "rss_item_info",
    foreignKeys = [
        ForeignKey(
            entity = RssItemEntity::class,
            parentColumns = [GUID_KEY],
            childColumns = [GUID_KEY],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = [GUID_KEY])]
)
data class RssItemInfoEntity(
    @PrimaryKey val guid: String,
    val isFavorite: Boolean = false,
    val hasBeenRead: Boolean = false
)

private const val GUID_KEY = "guid"
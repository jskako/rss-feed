package com.jskako.rssfeed.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rss")
data class RssEntity(
    @PrimaryKey val url: String,
    val description: String?,
    val image: ByteArray?
)
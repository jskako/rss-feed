package com.jskako.rssfeed.domain.model.database

import java.time.Instant

data class RssItem(
    val guid: String,
    val rss: String,
    val title: String?,
    val updateDate: Instant?,
    val expiresDate: Instant?,
    val link: String?,
    val description: String?,
    val imagePaths: List<String>?,
    val hasBeenRead: Boolean = false,
    val isFavorite: Boolean = false
)
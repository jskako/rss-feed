package com.jskako.rssfeed.domain.model

import java.time.Instant

data class RssChannel(
    val rss: String,
    val title: String?,
    val link: String?,
    val description: String?,
    val lastBuildDate: Instant?,
    val imagePath: String?
)
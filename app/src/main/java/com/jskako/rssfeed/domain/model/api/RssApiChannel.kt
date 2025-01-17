package com.jskako.rssfeed.domain.model.api

import java.time.Instant

data class RssApiChannel(
    val rss: String,
    val title: String? = null,
    val link: String? = null,
    val description: String? = null,
    val lastBuildDate: Instant? = null,
    val imagePath: String? = null
)
package com.jskako.rssfeed.domain.model.api

import java.time.Instant

data class RssApiItem(
    val guid: String,
    val rss: String,
    val title: String?,
    val link: String?,
    val description: String?,
    val updateDate: Instant?,
    val expiresDate: Instant?,
    val imagePaths: List<String>?
)
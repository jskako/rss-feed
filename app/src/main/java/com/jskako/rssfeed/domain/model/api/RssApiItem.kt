package com.jskako.rssfeed.domain.model.api

import java.time.Instant

data class RssApiItem(
    val guid: String,
    val rss: String,
    val title: String?,
    val link: String?,
    val updateDate: Instant?,
    val imagePaths: List<String>
)
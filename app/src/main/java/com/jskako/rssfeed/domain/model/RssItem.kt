package com.jskako.rssfeed.domain.model

import java.time.Instant

data class RssItem(
    val guid: String,
    val rss: String,
    val title: String?,
    val link: String?,
    val updateDate: Instant?,
    val imagePaths: List<String>
)
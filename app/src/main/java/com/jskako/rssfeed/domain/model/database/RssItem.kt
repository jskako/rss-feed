package com.jskako.rssfeed.domain.model.database

import java.time.Instant

data class RssItem(
    val guid: String,
    val rss: String,
    val title: String? = null,
    val updateDate: Instant? = null,
    val expiresDate: Instant? = null,
    val link: String? = null,
    val description: String? = null,
    val imagePaths: List<String>? = null,
    val hasBeenRead: Boolean = false,
    val isFavorite: Boolean = false
) {
    companion object {
        val mockerRssItem = RssItem(
            guid = "",
            rss = ""
        )
    }
}
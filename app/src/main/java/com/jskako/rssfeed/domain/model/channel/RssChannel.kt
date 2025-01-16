package com.jskako.rssfeed.domain.model.channel

import java.time.Instant

data class RssChannel(
    val rss: String,
    val title: String? = null,
    val link: String? = null,
    val description: String? = null,
    val lastBuildDate: Instant? = null,
    val imagePath: String? = null
) {
    companion object {
        val mockedRssChannel = RssChannel(
            rss = MOCKED_RSS
        )
    }
}

private const val MOCKED_RSS = "https://news.yahoo.com/rss/us"
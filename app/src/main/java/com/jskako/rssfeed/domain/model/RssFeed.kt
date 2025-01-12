package com.jskako.rssfeed.domain.model

/**
 * TODO:
 * Convert lastBuildDate from String to Date
 * Convert imagePath to Bitmap
 */
data class RssFeed(
    val rss: String,
    val title: String?,
    val description: String?,
    val link: String?,
    val lastBuildDate: String?,
    val imagePath: String?
)
package com.jskako.rssfeed.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class RssItemDto(
    val guid: String,
    val title: String,
    val link: String,
    val description: String,
    val pubDate: String
)

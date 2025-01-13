package com.jskako.rssfeed.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class RssChannel(
    val title: String,
    val link: String?,
    val description: String?,
    val lastBuildDate: String?,
    val item: List<RssItemDto>
)
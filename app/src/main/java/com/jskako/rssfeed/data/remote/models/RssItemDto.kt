package com.jskako.rssfeed.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RssItemDto(
    val guid: String,
    val title: String,
    val link: String,
    val description: String,
    val pubDate: String,
    val updateDate: String,
    @SerialName("media:content") val mediaContents: List<MediaContent> = emptyList()
)

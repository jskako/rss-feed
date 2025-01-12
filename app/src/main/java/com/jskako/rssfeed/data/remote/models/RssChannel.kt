package com.jskako.rssfeed.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class RssChannel(
    val items: List<RssItemDto>
)
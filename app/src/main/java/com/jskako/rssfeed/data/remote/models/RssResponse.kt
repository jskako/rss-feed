package com.jskako.rssfeed.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class RssResponse(
    val channel: RssChannel
)

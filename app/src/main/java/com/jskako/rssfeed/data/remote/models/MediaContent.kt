package com.jskako.rssfeed.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class MediaContent(
    val url: String,
    val type: String? = null,
    val medium: String? = null,
    val height: Int? = null,
    val width: Int? = null
)
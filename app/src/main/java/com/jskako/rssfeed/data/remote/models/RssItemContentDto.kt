package com.jskako.rssfeed.data.remote.models

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
data class RssItemContentDto(
    @JsonProperty("url") val url: String? = null,
)
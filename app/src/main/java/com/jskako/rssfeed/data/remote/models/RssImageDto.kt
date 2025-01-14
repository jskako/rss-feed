package com.jskako.rssfeed.data.remote.models

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
data class RssImageDto(
    @JsonProperty("url") val url: String,
    @JsonProperty("title") val title: String,
    @JsonProperty("link") val link: String
)
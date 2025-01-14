package com.jskako.rssfeed.data.remote.models

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
data class RssItemDto(
    @JsonProperty("title") val title: String? = null,
    @JsonProperty("link") val link: String? = null,
    @JsonProperty("updateDate") val updateDate: String? = null,
    @JsonProperty("guid") val guid: String,
    @JsonProperty("content") val content: RssItemContentDto? = null
)
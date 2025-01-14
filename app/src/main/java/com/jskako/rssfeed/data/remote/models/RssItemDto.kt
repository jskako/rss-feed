package com.jskako.rssfeed.data.remote.models

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
data class RssItemDto(
    @JsonProperty("title") val title: String? = null,
    @JsonProperty("link") val link: String? = null,
    @JsonProperty("pubDate") val pubDate: String? = null,
    @JsonProperty("guid") val guid: RssItemGuidDto? = null,
    @JsonProperty("content") val content: RssItemContentDto? = null
)
package com.jskako.rssfeed.data.remote.models

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
data class RssChannelDto(
    @JsonProperty("title") val title: String?,
    @JsonProperty("link") val link: String?,
    @JsonProperty("description") val description: String?,
    @JsonProperty("lastBuildDate") val lastBuildDate: String?,
    @JsonProperty("image") val image: RssImageDto?,
    @JsonProperty("item") val item: List<RssItemDto> = emptyList()
)


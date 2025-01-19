package com.jskako.rssfeed.data.remote.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.jskako.rssfeed.data.remote.serializers.LinkDeserializer
import kotlinx.serialization.Serializable

@Serializable
@JsonIgnoreProperties(ignoreUnknown = true)
data class RssChannelDto(
    @JsonProperty("title") val title: String?,
    @JsonProperty("link")
    @JsonDeserialize(using = LinkDeserializer::class)
    val link: String?,
    @JsonProperty("description") val description: String?,
    @JsonProperty("lastBuildDate") val lastBuildDate: String?,
    @JsonProperty("image") val image: RssImageDto?,
    @JsonProperty("item") val item: List<RssItemDto> = emptyList()
)


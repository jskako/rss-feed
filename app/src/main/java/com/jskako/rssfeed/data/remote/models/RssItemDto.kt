package com.jskako.rssfeed.data.remote.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.jskako.rssfeed.data.remote.serializers.GuidDeserializer
import kotlinx.serialization.Serializable

@Serializable
data class RssItemDto(
    @JsonProperty("title") val title: String? = null,
    @JsonProperty("link") val link: String? = null,
    @JsonProperty("updateDate") val updateDate: String? = null,
    @JsonProperty("guid")
    @JsonDeserialize(using = GuidDeserializer::class)
    val guid: String,
    @JsonProperty("content") val content: RssItemContentDto? = null
)
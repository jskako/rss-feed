package com.jskako.rssfeed.data.remote.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.jskako.rssfeed.data.remote.serializers.GuidDeserializer
import com.jskako.rssfeed.data.remote.serializers.RssItemContentDeserializer
import kotlinx.serialization.Serializable

@Serializable
@JsonIgnoreProperties(ignoreUnknown = true)
data class RssItemDto(
    @JsonProperty("title") val title: String? = null,
    @JsonProperty("link") val link: String? = null,
    @JsonProperty("updateDate") val updateDate: String? = null,
    @JsonProperty("expires") val expiresDate: String? = null,
    @JsonProperty("description") val description: String? = null,
    @JsonProperty("guid")
    @JsonDeserialize(using = GuidDeserializer::class)
    val guid: String,
    @JsonProperty("content")
    @JsonDeserialize(using = RssItemContentDeserializer::class)
    val content: List<RssItemContentDto>? = null

)
package com.jskako.rssfeed.data.remote.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
@JsonIgnoreProperties(ignoreUnknown = true)
data class RssResponseDto(
    @JsonProperty("channel") val channel: RssChannelDto
)
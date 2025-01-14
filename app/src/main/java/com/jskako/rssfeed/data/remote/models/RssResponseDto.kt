package com.jskako.rssfeed.data.remote.models

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
data class RssResponseDto(
    @JsonProperty("channel") val channel: RssChannelDto
)
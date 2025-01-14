package com.jskako.rssfeed.data.remote.models

import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
data class RssItemGuidDto(
    @JsonProperty("isPermaLink") val isPermaLink: String?,
    @JsonProperty("guid") var guid: String? = null
) {
    @JsonAnySetter
    fun setAdditionalProperty(key: String, value: String) {
        if (key.isEmpty() || key == "guid") {
            guid = value
        }
    }
}
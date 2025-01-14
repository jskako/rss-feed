package com.jskako.rssfeed.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("media:thumbnail", namespace = "", prefix = "media")
data class MediaThumbnail(
    @SerialName("url")
    val url: String,

    @SerialName("width")
    val width: Int? = null,

    @SerialName("height")
    val height: Int? = null
)
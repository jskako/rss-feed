package com.jskako.rssfeed.data.remote.models

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("rss", namespace = "", prefix = "")
data class RssResponse(
    val version: String,
    val channel: RssChannel
)
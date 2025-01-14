package com.jskako.rssfeed.data.remote.models

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlChildrenName
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("image", namespace = "", prefix = "")
data class RssImage(
    @XmlChildrenName("url", namespace = "")
    val url: String,
    @XmlChildrenName("title", namespace = "")
    val title: String,
    @XmlChildrenName("link", namespace = "")
    val link: String
)
package com.jskako.rssfeed.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlChildrenName
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("item", namespace = "", prefix = "")
data class RssItem(
    @XmlChildrenName("media:thumbnail", namespace = "", prefix = "")
    @SerialName("media:thumbnail")
    val mediaThumbnail: List<MediaThumbnail> = emptyList(),

    @XmlChildrenName("media:keywords", namespace = "", prefix = "media")
    val keywords: String? = null,

    @XmlChildrenName("title", namespace = "", prefix = "")
    val title: String? = null,

    @XmlChildrenName("link", namespace = "", prefix = "")
    val link: String? = null,

    @XmlChildrenName("guid", namespace = "", prefix = "")
    val guid: String? = null,

    @XmlChildrenName("pubDate", namespace = "", prefix = "")
    val pubDate: String? = null,

    @XmlChildrenName("description", namespace = "", prefix = "")
    val description: String? = null,

    @XmlSerialName("category", namespace = "", prefix = "")
    val category: String? = null
)
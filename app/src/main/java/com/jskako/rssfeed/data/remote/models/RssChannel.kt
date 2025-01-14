package com.jskako.rssfeed.data.remote.models

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlChildrenName
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("channel", namespace = "", prefix = "")
data class RssChannel(
    @XmlChildrenName("title", namespace = "")
    val title: String?,
    @XmlChildrenName("link", namespace = "")
    val link: String?,
    @XmlChildrenName("description", namespace = "")
    val description: String?,
    @XmlChildrenName("language", namespace = "")
    val language: String?,
    @XmlChildrenName("copyright", namespace = "")
    val copyright: String?,
    @XmlChildrenName("lastBuildDate", namespace = "")
    val lastBuildDate: String?,
    @XmlElement(true)
    val rssImage: RssImage?,
    @XmlChildrenName("item", namespace = "")
    val item: List<RssItem> = emptyList()
)


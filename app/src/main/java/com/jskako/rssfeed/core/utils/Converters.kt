package com.jskako.rssfeed.core.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.xml.XmlMapper

fun convertXmlToJson(xml: String): String? {
    return runCatching {
        XmlMapper().readValue(xml, Map::class.java).let {
            ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(it)
        }
    }.getOrNull()
}
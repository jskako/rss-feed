package com.jskako.rssfeed.core.utils

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.xml.XmlMapper

fun convertXmlToJsonString(xml: String): String? {
    return run {
        XmlMapper().readValue(xml, Map::class.java).let {
            ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(it)
        }
    }
}

inline fun <reified T> jsonToDataClass(json: String, ignoreUnknownKeys: Boolean = true): T? {
    return run {
        ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, !ignoreUnknownKeys)
            .readValue(json, T::class.java)
    }
}
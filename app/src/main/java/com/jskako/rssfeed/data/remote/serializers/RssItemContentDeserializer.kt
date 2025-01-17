package com.jskako.rssfeed.data.remote.serializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.jskako.rssfeed.data.remote.models.RssItemContentDto

class RssItemContentDeserializer : JsonDeserializer<List<RssItemContentDto>>() {
    override fun deserialize(
        parser: JsonParser,
        ctxt: DeserializationContext
    ): List<RssItemContentDto> {
        val node: JsonNode = parser.codec.readTree(parser)
        val mapper = jacksonObjectMapper()

        return if (node.isArray) {
            mapper.readValue(node.toString(), object : TypeReference<List<RssItemContentDto>>() {})
        } else {
            listOf(mapper.readValue(node.toString(), RssItemContentDto::class.java))
        }
    }
}
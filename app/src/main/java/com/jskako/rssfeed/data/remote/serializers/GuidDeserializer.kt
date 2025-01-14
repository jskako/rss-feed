package com.jskako.rssfeed.data.remote.serializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode

class GuidDeserializer : JsonDeserializer<String>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): String {
        val node: JsonNode = p.codec.readTree(p)
        return when {
            node.isTextual -> node.asText()
            node.isObject -> node[""]?.asText() ?: throw IllegalArgumentException("Invalid guid object format")
            else -> throw IllegalArgumentException("Unsupported guid format")
        }
    }
}
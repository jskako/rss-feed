package com.jskako.rssfeed.data.remote.api

import android.util.Log
import com.jskako.rssfeed.core.utils.convertXmlToJsonString
import com.jskako.rssfeed.core.utils.jsonToDataClass
import com.jskako.rssfeed.data.remote.models.RssResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class RssApi(private val client: HttpClient) {

    suspend fun fetchRss(link: String) = runCatching {
        val rssResponse = client.get(link).bodyAsText()
        val jsonOutput = convertXmlToJsonString(rssResponse)
        jsonOutput?.let {
            jsonToDataClass<RssResponseDto>(it)
        }
    }.getOrElse { e ->
        Log.e("RssApi", "Failed to fetch RSS", e)
        null
    }
}
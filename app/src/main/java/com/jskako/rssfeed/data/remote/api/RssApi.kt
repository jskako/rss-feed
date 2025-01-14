package com.jskako.rssfeed.data.remote.api

import android.util.Log
import com.jskako.rssfeed.core.utils.convertXmlToJson
import com.jskako.rssfeed.domain.model.RssFeed
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class RssApi(private val client: HttpClient) {

    suspend fun fetchRss(link: String): List<RssFeed> {
        return runCatching {
            val rssResponse = client.get(link).bodyAsText()
            val jsonOutput = convertXmlToJson(rssResponse)
            Log.e("123123", "$jsonOutput")
            return emptyList()
        }.onFailure { e ->
            Log.e("RssApi", "Failed to fetch RSS", e)
        }.getOrElse { emptyList() }
    }
}
package com.jskako.rssfeed.data.remote.api

import android.util.Log
import com.jskako.rssfeed.core.utils.convertXmlToJsonString
import com.jskako.rssfeed.core.utils.jsonToDataClass
import com.jskako.rssfeed.data.remote.mapper.toRssApiResponse
import com.jskako.rssfeed.data.remote.models.RssResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.head
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode

class RssApi(private val client: HttpClient) {

    suspend fun fetchRss(rss: String) = runCatching {
        val rssResponse = client.get(rss).bodyAsText()
        val jsonOutput = convertXmlToJsonString(rssResponse)
        jsonOutput?.let {
            jsonToDataClass<RssResponseDto>(it)
        }?.toRssApiResponse(rss = rss)
    }.getOrElse {
        Log.e("Fetch error", "${it.message}")
        null
    }

    suspend fun isUrlReachable(rss: String): Boolean {
        return runCatching {
            client.head(rss).status == HttpStatusCode.OK
        }.getOrElse {
            false
        }
    }
}
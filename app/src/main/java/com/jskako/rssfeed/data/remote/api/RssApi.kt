package com.jskako.rssfeed.data.remote.api

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

    suspend fun fetchRss(rssLink: String) = runCatching {
        val rssResponse = client.get(rssLink).bodyAsText()
        val jsonOutput = convertXmlToJsonString(rssResponse)
        jsonOutput?.let {
            jsonToDataClass<RssResponseDto>(it)
        }?.toRssApiResponse(rss = rssLink)
    }.getOrElse { e ->
        null
    }

    suspend fun isUrlReachable(link: String): Boolean {
        return runCatching {
            client.head(link).status == HttpStatusCode.OK
        }.getOrElse {
            false
        }
    }
}
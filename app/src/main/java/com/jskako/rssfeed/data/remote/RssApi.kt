package com.jskako.rssfeed.data.remote

import com.jskako.rssfeed.domain.model.RssFeed
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

class RssApi(private val client: HttpClient) {

    // TODO - Refactor fetching RSS
    suspend fun fetchRss(link: String): List<RssFeed> {
        return runCatching {
            val response: HttpResponse = client.get(link)
            return emptyList()
        }.getOrElse { emptyList() }
    }
}
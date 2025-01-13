package com.jskako.rssfeed.data.remote.api

import android.util.Log
import com.jskako.rssfeed.data.remote.models.RssResponse
import com.jskako.rssfeed.domain.model.RssFeed
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class RssApi(private val client: HttpClient) {

    // TODO - Refactor fetching RSS
    suspend fun fetchRss(link: String): List<RssFeed> {
        return runCatching {
            val response: RssResponse = client.get(link).body()
            Log.e("123123", "$response")
            return emptyList()
        }.getOrElse { emptyList() }
    }
}
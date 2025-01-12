package com.jskako.rssfeed.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.xml.xml
import nl.adaptivity.xmlutil.XmlDeclMode
import nl.adaptivity.xmlutil.serialization.XML

object RssNetworkModule {

    fun provideRssHttpClient(
        networkTimeout: NetworkTimeout = NetworkTimeout(),
        logLevel: LogLevel = LogLevel.ALL
    ): HttpClient {
        return HttpClient(CIO) {
            install(Logging) {
                level = logLevel
            }

            install(ContentNegotiation) {
                xml(
                    format = XML {
                        xmlDeclMode = XmlDeclMode.Charset
                    },
                    contentType = ContentType.Application.Rss
                )
            }

            install(HttpTimeout) {
                requestTimeoutMillis = networkTimeout.requestTimeoutMillis
                connectTimeoutMillis = networkTimeout.connectTimeoutMillis
                socketTimeoutMillis = networkTimeout.socketTimeoutMillis
            }
        }
    }
}
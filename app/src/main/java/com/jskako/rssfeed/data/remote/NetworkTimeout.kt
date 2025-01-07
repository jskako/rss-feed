package com.jskako.rssfeed.data.remote

data class NetworkTimeout(
    val requestTimeoutMillis: Long = REQUEST_TIMEOUT, // Set request timeout to 15 seconds
    val connectTimeoutMillis: Long = CONNECT_TIMEOUT,  // Set connection timeout to 5 seconds
    val socketTimeoutMillis: Long = SOCKET_TIMEOUT  // Set socket timeout to 10 seconds
)

private const val REQUEST_TIMEOUT: Long = 15_000L
private const val CONNECT_TIMEOUT: Long = 5_000L
private const val SOCKET_TIMEOUT: Long = 10_000L
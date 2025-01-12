package com.jskako.rssfeed.data.remote

import org.junit.Assert.assertNotNull
import org.junit.Test

class NetworkModuleTest {

    @Test
    fun `test HttpClient is created successfully`() {
        val httpClient = NetworkModule.provideRssHttpClient()
        assertNotNull("HttpClient should not be null", httpClient)
    }
}

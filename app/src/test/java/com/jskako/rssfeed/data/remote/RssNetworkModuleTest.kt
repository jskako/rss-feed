package com.jskako.rssfeed.data.remote

import org.junit.Assert.assertNotNull
import org.junit.Test

class RssNetworkModuleTest {

    @Test
    fun `test HttpClient is created successfully`() {
        val httpClient = RssNetworkModule.provideRssHttpClient()
        assertNotNull("HttpClient should not be null", httpClient)
    }
}

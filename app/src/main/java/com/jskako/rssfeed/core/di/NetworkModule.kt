package com.jskako.rssfeed.core.di

import com.jskako.rssfeed.data.remote.RssApi
import com.jskako.rssfeed.data.remote.RssNetworkModule
import org.koin.dsl.module

val networkModule = module {

    single { RssNetworkModule.provideRssHttpClient() }
    single { RssApi(get()) }
}
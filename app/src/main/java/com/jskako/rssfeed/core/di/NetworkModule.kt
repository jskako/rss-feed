package com.jskako.rssfeed.core.di

import com.jskako.rssfeed.data.remote.RssNetworkModule
import com.jskako.rssfeed.data.remote.api.RssApi
import com.jskako.rssfeed.data.remote.repository.RssApiRepositoryImpl
import com.jskako.rssfeed.domain.repository.RssApiRepository
import org.koin.dsl.module

val networkModule = module {

    single { RssNetworkModule.provideRssHttpClient() }
    single<RssApiRepository> { RssApiRepositoryImpl(get()) }

    single { RssApi(get()) }
}
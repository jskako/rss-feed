package com.jskako.rssfeed.core.di

import com.jskako.rssfeed.domain.usecase.rss.api.ApiUseCases
import com.jskako.rssfeed.domain.usecase.rss.api.CheckUrlReachabilityUseCase
import com.jskako.rssfeed.domain.usecase.rss.api.FetchRssFeedUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.DatabaseUseCases
import com.jskako.rssfeed.domain.usecase.rss.database.GetRssChannelUseCase
import org.koin.dsl.module

val useCasesModule = module {

    factory { GetRssChannelUseCase(get()) }
    factory { DatabaseUseCases(get()) }

    factory { FetchRssFeedUseCase(get()) }
    factory { CheckUrlReachabilityUseCase(get()) }
    factory { ApiUseCases(get(), get()) }
}
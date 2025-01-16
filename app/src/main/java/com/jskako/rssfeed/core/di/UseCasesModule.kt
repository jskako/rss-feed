package com.jskako.rssfeed.core.di

import com.jskako.rssfeed.domain.usecase.rss.api.ApiUseCases
import com.jskako.rssfeed.domain.usecase.rss.api.CheckUrlReachabilityUseCase
import com.jskako.rssfeed.domain.usecase.rss.api.FetchRssFeedUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.ChannelExistUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.DatabaseUseCases
import com.jskako.rssfeed.domain.usecase.rss.database.DeleteRssChannelUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.GetLastBuildDateUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.GetRssChannelUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.GetRssChannelsUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.InsertRssChannelUseCase
import org.koin.dsl.module

val useCasesModule = module {

    // Database
    factory { GetRssChannelsUseCase(get()) }
    factory { DeleteRssChannelUseCase(get()) }
    factory { GetRssChannelUseCase(get()) }
    factory { InsertRssChannelUseCase(get()) }
    factory { GetLastBuildDateUseCase(get()) }
    factory { ChannelExistUseCase(get()) }
    factory { DatabaseUseCases(get(), get(), get(), get(), get(), get()) }

    factory { FetchRssFeedUseCase(get()) }
    factory { CheckUrlReachabilityUseCase(get()) }
    factory { ApiUseCases(get(), get()) }
}
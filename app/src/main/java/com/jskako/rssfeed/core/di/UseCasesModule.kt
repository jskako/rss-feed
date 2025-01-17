package com.jskako.rssfeed.core.di

import com.jskako.rssfeed.domain.usecase.rss.api.ApiUseCases
import com.jskako.rssfeed.domain.usecase.rss.api.CheckUrlReachabilityUseCase
import com.jskako.rssfeed.domain.usecase.rss.api.FetchRssFeedUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.DatabaseChannelUseCases
import com.jskako.rssfeed.domain.usecase.rss.database.channel.ChannelExistUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.channel.DeleteRssChannelUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.channel.GetLastBuildDateUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.channel.GetRssChannelUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.channel.GetRssChannelsUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.channel.InsertRssChannelUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.channel.IsNotificationEnabledUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.channel.UpdateNotificationUseCase
import org.koin.dsl.module

val useCasesModule = module {

    // Database channels
    factory { GetRssChannelsUseCase(get()) }
    factory { DeleteRssChannelUseCase(get()) }
    factory { GetRssChannelUseCase(get()) }
    factory { InsertRssChannelUseCase(get()) }
    factory { GetLastBuildDateUseCase(get()) }
    factory { ChannelExistUseCase(get()) }
    factory { IsNotificationEnabledUseCase(get()) }
    factory { UpdateNotificationUseCase(get()) }
    factory { DatabaseChannelUseCases(get(), get(), get(), get(), get(), get(), get(), get()) }

    factory { FetchRssFeedUseCase(get()) }
    factory { CheckUrlReachabilityUseCase(get()) }
    factory { ApiUseCases(get(), get()) }
}
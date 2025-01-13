package com.jskako.rssfeed.core.di

import com.jskako.rssfeed.domain.usecase.rss.api.ApiUseCases
import com.jskako.rssfeed.domain.usecase.rss.api.FetchRssFeedUseCase
import com.jskako.rssfeed.domain.usecase.rss.feed.GetRssFeedsUseCase
import com.jskako.rssfeed.domain.usecase.rss.feed.RssUseCases
import org.koin.dsl.module

val useCasesModule = module {

    factory { GetRssFeedsUseCase(get()) }
    factory { RssUseCases(get()) }

    factory { FetchRssFeedUseCase(get()) }
    factory { ApiUseCases(get()) }
}
package com.jskako.rssfeed.core.di

import com.jskako.rssfeed.domain.usecase.rss.GetRssFeedsUseCase
import com.jskako.rssfeed.domain.usecase.rss.RssUseCases
import org.koin.dsl.module

val useCasesModule = module {

    factory { GetRssFeedsUseCase(get()) }
    factory { RssUseCases(get()) }
}
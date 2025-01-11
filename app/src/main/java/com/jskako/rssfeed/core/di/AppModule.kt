package com.jskako.rssfeed.core.di

import androidx.room.Room
import com.jskako.rssfeed.data.local.database.AppDatabase
import com.jskako.rssfeed.data.local.database.AppDatabase.Companion.DATABASE_NAME
import com.jskako.rssfeed.data.repository.RssRepositoryImpl
import com.jskako.rssfeed.domain.repository.RssRepository
import com.jskako.rssfeed.domain.usecase.rss.GetRssItemsUseCase
import com.jskako.rssfeed.domain.usecase.rss.RssUseCases
import com.jskako.rssfeed.presentation.viewmodel.RssViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, DATABASE_NAME)
            .build()
    }
    single { get<AppDatabase>().rssDao() }

    single<RssRepository> { RssRepositoryImpl(get()) }

    factory { GetRssItemsUseCase(get()) }
    factory { RssUseCases(get()) }

    viewModelOf(::RssViewModel)
}
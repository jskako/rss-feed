package com.jskako.rssfeed.core.di

import androidx.room.Room
import com.jskako.rssfeed.data.local.database.AppDatabase
import com.jskako.rssfeed.data.local.database.AppDatabase.Companion.DATABASE_NAME
import com.jskako.rssfeed.data.local.repository.RssFeedRepositoryImpl
import com.jskako.rssfeed.domain.repository.RssFeedRepository
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, DATABASE_NAME)
            .build()
    }

    single { get<AppDatabase>().rssDao() }
    single<RssFeedRepository> { RssFeedRepositoryImpl(get()) }
}
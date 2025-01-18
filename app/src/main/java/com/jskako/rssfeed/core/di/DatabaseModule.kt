package com.jskako.rssfeed.core.di

import androidx.room.Room
import com.jskako.rssfeed.data.local.database.AppDatabase
import com.jskako.rssfeed.data.local.database.AppDatabase.Companion.DATABASE_NAME
import com.jskako.rssfeed.data.local.repository.RssChannelRepositoryImpl
import com.jskako.rssfeed.data.local.repository.RssItemIRepositoryImpl
import com.jskako.rssfeed.domain.repository.RssChannelRepository
import com.jskako.rssfeed.domain.repository.RssItemRepository
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, DATABASE_NAME)
            .build()
    }

    single { get<AppDatabase>().rssChannelDao() }
    single { get<AppDatabase>().rssItemDao() }
    single<RssChannelRepository> { RssChannelRepositoryImpl(get()) }
    single<RssItemRepository> { RssItemIRepositoryImpl(get()) }
}
package com.jskako.rssfeed.core.di

import androidx.room.Room
import com.jskako.rssfeed.data.local.database.AppDatabase
import com.jskako.rssfeed.data.local.database.AppDatabase.Companion.DATABASE_NAME
import com.jskako.rssfeed.data.local.repository.RssChannelInfoRepositoryImpl
import com.jskako.rssfeed.data.local.repository.RssChannelRepositoryImpl
import com.jskako.rssfeed.domain.repository.channel.RssChannelInfoRepository
import com.jskako.rssfeed.domain.repository.channel.RssChannelRepository
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, DATABASE_NAME)
            .build()
    }

    single { get<AppDatabase>().rssDao() }
    single { get<AppDatabase>().rssInfoDao() }
    single<RssChannelRepository> { RssChannelRepositoryImpl(get()) }
    single<RssChannelInfoRepository> { RssChannelInfoRepositoryImpl(get()) }
}
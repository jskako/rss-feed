package com.jskako.rssfeed.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.jskako.rssfeed.data.local.database.AppDatabase
import com.jskako.rssfeed.data.local.database.AppDatabase.Companion.DATABASE_NAME
import com.jskako.rssfeed.data.local.repository.PreferencesRepositoryImpl
import com.jskako.rssfeed.data.local.repository.RssChannelRepositoryImpl
import com.jskako.rssfeed.data.local.repository.RssItemIRepositoryImpl
import com.jskako.rssfeed.domain.repository.PreferencesRepository
import com.jskako.rssfeed.domain.repository.RssChannelRepository
import com.jskako.rssfeed.domain.repository.RssItemRepository
import org.koin.dsl.module

private const val DATASTORE_NAME = "rss_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

val databaseModule = module {

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, DATABASE_NAME)
            .build()
    }

    single { get<AppDatabase>().rssChannelDao() }
    single { get<AppDatabase>().rssItemDao() }
    single<RssChannelRepository> { RssChannelRepositoryImpl(get()) }
    single<RssItemRepository> { RssItemIRepositoryImpl(get()) }

    single<DataStore<Preferences>> { get<Context>().dataStore }
    single<PreferencesRepository> { PreferencesRepositoryImpl(get()) }
}
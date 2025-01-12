package com.jskako.rssfeed.core

import android.app.Application
import com.jskako.rssfeed.core.di.databaseModule
import com.jskako.rssfeed.core.di.networkModule
import com.jskako.rssfeed.core.di.useCasesModule
import com.jskako.rssfeed.core.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RssApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@RssApp)
            modules(databaseModule, useCasesModule, viewModelsModule, networkModule)
        }
    }
}
package com.jskako.rssfeed.core

import android.app.Application
import com.jskako.rssfeed.core.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RssApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@RssApp)
            modules(appModule)
        }
    }
}
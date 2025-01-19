package com.jskako.rssfeed.core.di

import android.app.Application
import com.jskako.rssfeed.data.worker.RssWorker
import com.jskako.rssfeed.domain.notifications.NotificationSender
import com.jskako.rssfeed.presentation.notification.NotificationHelper
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.dsl.module

val workerModule = module {
    factory<NotificationSender> { (context: Application) -> NotificationHelper(context) }
    workerOf(::RssWorker)
}
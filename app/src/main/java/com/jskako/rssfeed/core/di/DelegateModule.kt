package com.jskako.rssfeed.core.di

import com.jskako.rssfeed.presentation.delegate.database.DatabaseDelegate
import com.jskako.rssfeed.presentation.delegate.database.DatabaseDelegateImpl
import com.jskako.rssfeed.presentation.delegate.worker.WorkerDelegate
import com.jskako.rssfeed.presentation.delegate.worker.WorkerDelegateImpl
import org.koin.dsl.module

val delegateModule = module {

    factory<DatabaseDelegate> {
        DatabaseDelegateImpl(
            databaseChannelUseCases = get(),
            databaseItemUseCases = get()
        )
    }

    factory<WorkerDelegate> {
        WorkerDelegateImpl(
            context = get()
        )
    }
}
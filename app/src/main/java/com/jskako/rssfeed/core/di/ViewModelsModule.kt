package com.jskako.rssfeed.core.di

import com.jskako.rssfeed.presentation.viewmodel.InitViewModel
import com.jskako.rssfeed.presentation.viewmodel.NetworkViewModel
import com.jskako.rssfeed.presentation.viewmodel.RssViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelsModule = module {
    viewModelOf(::RssViewModel)
    viewModelOf(::InitViewModel)
    viewModelOf(::NetworkViewModel)
}
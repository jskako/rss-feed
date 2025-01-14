package com.jskako.rssfeed.domain.usecase.rss.api

data class ApiUseCases(
    val fetchRssFeeds: FetchRssFeedUseCase,
    val isUrlReachable: CheckUrlReachabilityUseCase
)
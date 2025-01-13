package com.jskako.rssfeed.domain.usecase.rss.feed

import com.jskako.rssfeed.domain.repository.RssFeedRepository

class GetRssFeedsUseCase(
    private val repository: RssFeedRepository
) {
    operator fun invoke() = repository.getAllRss()
}
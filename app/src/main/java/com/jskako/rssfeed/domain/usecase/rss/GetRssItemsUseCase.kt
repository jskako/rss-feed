package com.jskako.rssfeed.domain.usecase.rss

import com.jskako.rssfeed.domain.repository.RssRepository

class GetRssItemsUseCase(
    private val repository: RssRepository
) {
    operator fun invoke() = repository.getAllRss()
}
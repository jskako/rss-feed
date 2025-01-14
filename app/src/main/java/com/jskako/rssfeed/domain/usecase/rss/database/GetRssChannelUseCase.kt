package com.jskako.rssfeed.domain.usecase.rss.database

import com.jskako.rssfeed.domain.repository.RssChannelRepository

class GetRssChannelUseCase(
    private val repository: RssChannelRepository
) {
    operator fun invoke() = repository.getAllRss()
}
package com.jskako.rssfeed.domain.usecase.rss.database.channel

import com.jskako.rssfeed.domain.repository.RssChannelRepository

class GetRssChannelsUseCase(
    private val repository: RssChannelRepository
) {
    operator fun invoke() = repository.getAllRss()
}
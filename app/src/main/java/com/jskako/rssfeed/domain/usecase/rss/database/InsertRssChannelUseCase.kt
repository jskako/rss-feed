package com.jskako.rssfeed.domain.usecase.rss.database

import com.jskako.rssfeed.domain.model.RssChannel
import com.jskako.rssfeed.domain.repository.RssChannelRepository

class InsertRssChannelUseCase(
    private val repository: RssChannelRepository
) {
    suspend operator fun invoke(rssChannel: RssChannel) {
        repository.insertRss(rssChannel)
    }
}
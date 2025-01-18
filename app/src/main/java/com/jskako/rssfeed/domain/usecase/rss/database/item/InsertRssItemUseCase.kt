package com.jskako.rssfeed.domain.usecase.rss.database.item

import com.jskako.rssfeed.domain.model.database.RssItem
import com.jskako.rssfeed.domain.repository.RssItemRepository

class InsertRssItemUseCase(
    private val repository: RssItemRepository
) {
    suspend operator fun invoke(rssItem: RssItem) {
        repository.insert(rssItem)
    }
}
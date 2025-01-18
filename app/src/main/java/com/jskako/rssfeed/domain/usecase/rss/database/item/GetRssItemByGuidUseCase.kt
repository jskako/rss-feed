package com.jskako.rssfeed.domain.usecase.rss.database.item

import com.jskako.rssfeed.domain.model.database.RssItem
import com.jskako.rssfeed.domain.repository.RssItemRepository

class GetRssItemByGuidUseCase(
    private val repository: RssItemRepository
) {
    suspend operator fun invoke(guid: String): RssItem? {
        return repository.get(guid)
    }
}
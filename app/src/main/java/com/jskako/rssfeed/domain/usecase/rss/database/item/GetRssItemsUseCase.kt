package com.jskako.rssfeed.domain.usecase.rss.database.item

import com.jskako.rssfeed.domain.model.database.RssItem
import com.jskako.rssfeed.domain.repository.RssItemRepository
import kotlinx.coroutines.flow.Flow

class GetRssItemsUseCase(
    private val repository: RssItemRepository
) {
    operator fun invoke(rss: String): Flow<List<RssItem>> {
        return repository.getItems(rss)
    }
}
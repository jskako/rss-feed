package com.jskako.rssfeed.domain.usecase.rss.database.item

import com.jskako.rssfeed.domain.repository.RssItemRepository
import kotlinx.coroutines.flow.Flow

class GetUnreadItemsCountUseCase(
    private val repository: RssItemRepository
) {
    operator fun invoke(rss: String): Flow<Int> {
        return repository.unreadItems(rss)
    }
}
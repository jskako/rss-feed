package com.jskako.rssfeed.domain.usecase.rss.database.item

import com.jskako.rssfeed.domain.repository.RssItemRepository

class GetUnreadItemsCountUseCase(
    private val repository: RssItemRepository
) {
    suspend operator fun invoke(rss: String): Int {
        return repository.unreadItems(rss)
    }
}
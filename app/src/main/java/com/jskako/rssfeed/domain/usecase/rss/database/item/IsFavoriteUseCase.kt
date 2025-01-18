package com.jskako.rssfeed.domain.usecase.rss.database.item

import com.jskako.rssfeed.domain.repository.RssItemRepository

class IsFavoriteUseCase(
    private val repository: RssItemRepository
) {
    suspend operator fun invoke(guid: String): Boolean {
        return repository.isFavorite(guid)
    }
}
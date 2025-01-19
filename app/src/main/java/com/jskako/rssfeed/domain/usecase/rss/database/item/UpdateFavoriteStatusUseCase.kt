package com.jskako.rssfeed.domain.usecase.rss.database.item

import com.jskako.rssfeed.domain.repository.RssItemRepository

class UpdateFavoriteStatusUseCase(
    private val repository: RssItemRepository
) {
    suspend operator fun invoke(guid: String, isFavorite: Boolean) {
        repository.updateFavorite(guid, isFavorite)
    }
}
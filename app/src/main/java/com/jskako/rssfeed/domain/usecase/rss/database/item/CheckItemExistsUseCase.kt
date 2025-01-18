package com.jskako.rssfeed.domain.usecase.rss.database.item

import com.jskako.rssfeed.domain.repository.RssItemRepository

class CheckItemExistsUseCase(
    private val repository: RssItemRepository
) {
    suspend operator fun invoke(guid: String): Boolean {
        return repository.itemExists(guid)
    }
}
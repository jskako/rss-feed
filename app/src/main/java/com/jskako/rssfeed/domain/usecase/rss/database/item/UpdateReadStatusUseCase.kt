package com.jskako.rssfeed.domain.usecase.rss.database.item

import com.jskako.rssfeed.domain.repository.RssItemRepository


class UpdateReadStatusUseCase(
    private val repository: RssItemRepository
) {
    suspend operator fun invoke(guid: String, hasBeenRead: Boolean) {
        repository.updateReadStatus(guid, hasBeenRead)
    }
}
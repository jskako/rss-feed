package com.jskako.rssfeed.domain.usecase.rss.database.item

import com.jskako.rssfeed.domain.repository.RssItemRepository
import java.time.Instant

class GetLastUpdateDateUseCase(
    private val repository: RssItemRepository
) {
    suspend operator fun invoke(url: String): Instant? {
        return repository.getLastUpdateDate(url)
    }
}
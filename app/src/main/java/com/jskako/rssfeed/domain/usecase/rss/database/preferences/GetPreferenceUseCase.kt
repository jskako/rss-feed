package com.jskako.rssfeed.domain.usecase.rss.database.preferences

import com.jskako.rssfeed.domain.repository.PreferencesRepository

class GetPreferenceUseCase(
    private val repository: PreferencesRepository
) {
    suspend operator fun invoke(key: String): String? {
        return repository.get(key = key)
    }
}
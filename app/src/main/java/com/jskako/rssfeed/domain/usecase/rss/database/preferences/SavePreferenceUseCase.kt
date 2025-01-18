package com.jskako.rssfeed.domain.usecase.rss.database.preferences

import com.jskako.rssfeed.domain.repository.PreferencesRepository

class SavePreferenceUseCase(
    private val repository: PreferencesRepository
) {
    suspend operator fun invoke(key: String, value: String) {
        repository.save(key = key, value = value)
    }
}
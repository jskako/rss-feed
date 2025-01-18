package com.jskako.rssfeed.domain.usecase.rss.database

import com.jskako.rssfeed.domain.usecase.rss.database.preferences.GetPreferenceUseCase
import com.jskako.rssfeed.domain.usecase.rss.database.preferences.SavePreferenceUseCase

data class PreferencesUseCases(
    val savePreference: SavePreferenceUseCase,
    val getPreference: GetPreferenceUseCase
)
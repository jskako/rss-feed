package com.jskako.rssfeed.data.local.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.jskako.rssfeed.domain.repository.PreferencesRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class PreferencesRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : PreferencesRepository {

    override suspend fun save(key: String, value: String) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }

    override suspend fun get(key: String): String? {
        val preferenceKey = stringPreferencesKey(key)
        return dataStore.data
            .map { preferences -> preferences[preferenceKey] }
            .firstOrNull()
    }
}
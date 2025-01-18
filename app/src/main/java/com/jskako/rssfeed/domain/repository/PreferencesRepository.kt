package com.jskako.rssfeed.domain.repository

interface PreferencesRepository {
    suspend fun save(key: String, value: String)
    suspend fun get(key: String): String?
}

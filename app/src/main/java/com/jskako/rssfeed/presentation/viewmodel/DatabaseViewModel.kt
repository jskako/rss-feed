package com.jskako.rssfeed.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jskako.rssfeed.domain.model.database.RssChannel
import com.jskako.rssfeed.domain.model.database.RssItem
import com.jskako.rssfeed.domain.usecase.rss.database.DatabaseChannelUseCases
import kotlinx.coroutines.launch
import java.time.Instant

abstract class DatabaseRssViewModel(
    protected val databaseChannelUseCases: DatabaseChannelUseCases
) : ViewModel() {

    fun updateNotification(rssLink: String, isEnabled: Boolean) = viewModelScope.launch {
        databaseChannelUseCases.updateNotification(rss = rssLink, isEnabled = isEnabled)
    }

    protected suspend fun isChannelUpdated(
        rssLink: String,
        currentDate: Instant?
    ): Boolean {
        return databaseChannelUseCases.getLastBuildDate(url = rssLink)?.isBefore(currentDate)
            ?: true
    }

    protected suspend fun channelExist(
        rssLink: String
    ) = databaseChannelUseCases.channelExist(rss = rssLink)

    protected suspend fun addChannelToDatabase(
        rssChannel: RssChannel
    ) {
        databaseChannelUseCases.insertRssChannel(rssChannel = rssChannel)
    }

    protected suspend fun addItemsToDatabase(
        rssItems: List<RssItem>
    ) {
        rssItems.forEach { item ->
            // Your logic here
        }
    }
}
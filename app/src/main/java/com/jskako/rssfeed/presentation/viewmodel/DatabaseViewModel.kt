package com.jskako.rssfeed.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jskako.rssfeed.domain.model.database.RssChannel
import com.jskako.rssfeed.domain.model.database.RssItem
import com.jskako.rssfeed.domain.usecase.rss.database.DatabaseChannelUseCases
import com.jskako.rssfeed.domain.usecase.rss.database.DatabaseItemUseCases
import kotlinx.coroutines.launch
import java.time.Instant

abstract class DatabaseRssViewModel(
    protected val databaseChannelUseCases: DatabaseChannelUseCases,
    private val databaseItemUseCases: DatabaseItemUseCases
) : ViewModel() {

    fun updateNotification(rss: String, isEnabled: Boolean) = viewModelScope.launch {
        databaseChannelUseCases.updateNotification(rss = rss, isEnabled = isEnabled)
    }

    protected suspend fun isChannelUpdated(
        rss: String,
        currentDate: Instant?
    ): Boolean {
        return databaseChannelUseCases.getLastBuildDate(url = rss)?.isBefore(currentDate)
            ?: !channelExist(rss = rss)
    }

    protected suspend fun addChannelToDatabase(
        rssChannel: RssChannel
    ) {
        databaseChannelUseCases.insertRssChannel(rssChannel = rssChannel)
    }

    protected suspend fun addItemsToDatabase(
        rssItems: List<RssItem>
    ) {
        rssItems.forEach { item ->
            if (isItemUpdated(guid = item.guid, currentDate = item.updateDate)) {
                databaseItemUseCases.insertRssItem(rssItem = item)
            }
        }
    }

    protected suspend fun channelExist(
        rss: String
    ) = databaseChannelUseCases.channelExist(rss = rss)

    private suspend fun itemExist(
        guid: String
    ) = databaseItemUseCases.itemExists(guid = guid)

    private suspend fun isItemUpdated(
        guid: String,
        currentDate: Instant?
    ): Boolean {
        return databaseItemUseCases.getLastUpdateDate(guid = guid)?.isBefore(currentDate)
            ?: !itemExist(guid = guid)
    }
}
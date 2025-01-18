package com.jskako.rssfeed.presentation.delegate.database

import com.jskako.rssfeed.domain.model.database.RssChannel
import com.jskako.rssfeed.domain.model.database.RssItem
import com.jskako.rssfeed.domain.usecase.rss.database.DatabaseChannelUseCases
import com.jskako.rssfeed.domain.usecase.rss.database.DatabaseItemUseCases
import java.time.Instant

class DatabaseDelegateImpl(
    private val databaseChannelUseCases: DatabaseChannelUseCases,
    private val databaseItemUseCases: DatabaseItemUseCases
) : DatabaseDelegate {

    override suspend fun updateNotification(rss: String, isEnabled: Boolean) {
        databaseChannelUseCases.updateNotification(rss = rss, isEnabled = isEnabled)
    }

    override suspend fun isChannelUpdated(rss: String, currentDate: Instant?): Boolean {
        return databaseChannelUseCases.getLastBuildDate(url = rss)?.isBefore(currentDate)
            ?: !channelExist(rss)
    }

    override suspend fun addChannelToDatabase(rssChannel: RssChannel) {
        databaseChannelUseCases.insertRssChannel(rssChannel)
    }

    override suspend fun addItemsToDatabase(rssItems: List<RssItem>) {
        rssItems.forEach { item ->
            if (isItemUpdated(item.guid, item.updateDate)) {
                databaseItemUseCases.insertRssItem(item)
            }
        }
    }

    override suspend fun channelExist(rss: String): Boolean {
        return databaseChannelUseCases.channelExist(rss)
    }

    private suspend fun isItemUpdated(guid: String, currentDate: Instant?): Boolean {
        return databaseItemUseCases.getLastUpdateDate(guid)?.isBefore(currentDate)
            ?: !databaseItemUseCases.itemExists(guid)
    }
}

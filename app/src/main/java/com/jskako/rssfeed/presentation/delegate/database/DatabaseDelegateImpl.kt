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

    override suspend fun isChannelUpdated(rss: String, lastBuildDate: Instant?): Boolean {
        return databaseChannelUseCases.getLastBuildDate(url = rss)?.isBefore(lastBuildDate)
            ?: !channelExist(rss)
    }

    override suspend fun channelExist(rss: String): Boolean {
        return databaseChannelUseCases.channelExist(rss)
    }

    override suspend fun addToDatabase(
        rss: String,
        rssChannel: RssChannel,
        rssItems: List<RssItem>
    ) {
        if (isChannelUpdated(rss = rss, lastBuildDate = rssChannel.lastBuildDate)) {
            addChannelToDatabase(
                rssChannel = rssChannel
            )
            addItemsToDatabase(
                rssItems = rssItems
            )
        }
    }

    private suspend fun addChannelToDatabase(rssChannel: RssChannel) {
        databaseChannelUseCases.insertRssChannel(rssChannel)
    }

    private suspend fun addItemsToDatabase(rssItems: List<RssItem>) {
        rssItems.forEach { item ->
            if (isItemUpdated(item.guid, item.updateDate)) {
                databaseItemUseCases.insertRssItem(item)
            }
        }
    }

    private suspend fun isItemUpdated(guid: String, currentDate: Instant?): Boolean {
        return databaseItemUseCases.getLastUpdateDate(guid)?.isBefore(currentDate)
            ?: !databaseItemUseCases.itemExists(guid)
    }
}

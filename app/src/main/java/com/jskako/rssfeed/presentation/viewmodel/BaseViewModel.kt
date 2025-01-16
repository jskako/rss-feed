package com.jskako.rssfeed.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.jskako.rssfeed.domain.model.RssChannel
import com.jskako.rssfeed.domain.model.RssItem
import com.jskako.rssfeed.domain.usecase.rss.database.DatabaseUseCases
import java.time.Instant

abstract class BaseRssViewModel(
    protected val databaseUseCases: DatabaseUseCases
) : ViewModel() {

    protected suspend fun isChannelUpdated(
        rssLink: String,
        currentDate: Instant?
    ): Boolean {
        return databaseUseCases.getLastBuildDate(url = rssLink)?.isBefore(currentDate) ?: true
    }

    protected suspend fun channelExist(
        rssLink: String
    ) = databaseUseCases.channelExist(rss = rssLink)

    protected suspend fun addChannelToDatabase(
        rssChannel: RssChannel
    ) {
        databaseUseCases.insertRssChannel(rssChannel = rssChannel)
    }

    protected suspend fun addItemsToDatabase(
        rssItems: List<RssItem>
    ) {
        rssItems.forEach { item ->
            // Your logic here
        }
    }
}
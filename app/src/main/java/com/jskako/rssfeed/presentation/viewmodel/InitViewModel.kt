package com.jskako.rssfeed.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.jskako.rssfeed.domain.mapper.toRssChannel
import com.jskako.rssfeed.domain.mapper.toRssItems
import com.jskako.rssfeed.domain.usecase.rss.api.ApiUseCases
import com.jskako.rssfeed.domain.usecase.rss.database.DatabaseChannelUseCases
import com.jskako.rssfeed.domain.usecase.rss.database.DatabaseItemUseCases
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class InitViewModel(
    private val apiUseCases: ApiUseCases,
    databaseChannelUseCases: DatabaseChannelUseCases,
    databaseItemUseCases: DatabaseItemUseCases,
) : DatabaseRssViewModel(databaseChannelUseCases, databaseItemUseCases) {


    fun fetchRssFeeds(
        onCurrentStepIndex: ((Int) -> Unit)? = null,
        onTotalSteps: ((Int) -> Unit)? = null,
        onDone: () -> Unit
    ) = viewModelScope.launch {

        val rssList = databaseChannelUseCases.getRssChannels().first().map { it.rss }
        onTotalSteps?.let {
            it(rssList.size)
        }
        rssList.forEachIndexed { index, rss ->
            onCurrentStepIndex?.let {
                it(index.inc())
            }

            runCatching {

                val feeds = apiUseCases.fetchRssFeeds(rss = rss)
                    ?: throw Exception("Failed to fetch feeds for $rss")

                if (isChannelUpdated(
                        rss = rss,
                        currentDate = feeds.rssApiChannel.lastBuildDate
                    )
                ) {
                    addChannelToDatabase(
                        rssChannel = feeds.rssApiChannel.toRssChannel(
                            databaseChannelUseCases = databaseChannelUseCases
                        )
                    )
                    addItemsToDatabase(
                        rssItems = feeds.rssApiItems.toRssItems(
                            databaseChannelUseCases = databaseChannelUseCases
                        )
                    )
                }
            }.onFailure {
                Log.e("Error", "$it")
            }
        }
        onDone()
    }
}
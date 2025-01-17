package com.jskako.rssfeed.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.jskako.rssfeed.domain.mapper.toRssChannel
import com.jskako.rssfeed.domain.usecase.rss.api.ApiUseCases
import com.jskako.rssfeed.domain.usecase.rss.database.DatabaseChannelUseCases
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class InitViewModel(
    private val apiUseCases: ApiUseCases,
    databaseChannelUseCases: DatabaseChannelUseCases
) : DatabaseRssViewModel(databaseChannelUseCases) {


    fun fetchRssFeeds(
        onCurrentStepIndex: ((Int) -> Unit)? = null,
        onTotalSteps: ((Int) -> Unit)? = null,
        onDone: () -> Unit
    ) = viewModelScope.launch {

        val rssList = databaseChannelUseCases.getRssChannels().first().map { it.rss }
        onTotalSteps?.let {
            it(rssList.size)
        }
        rssList.forEachIndexed { index, link ->
            onCurrentStepIndex?.let {
                it(index.inc())
            }

            runCatching {

                val feeds = apiUseCases.fetchRssFeeds(link = link)
                    ?: throw Exception("Failed to fetch feeds for $link")

                if (isChannelUpdated(
                        rssLink = link,
                        currentDate = feeds.rssApiChannel.lastBuildDate
                    )
                ) {
                    addChannelToDatabase(
                        rssChannel = feeds.rssApiChannel.toRssChannel(
                            databaseChannelUseCases = databaseChannelUseCases
                        )
                    )
                    //addItemsToDatabase(rssItems = feeds.rssItems)
                }
            }.onFailure {
                Log.e("Error", "$it")
            }
        }
        onDone()
    }
}
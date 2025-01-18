package com.jskako.rssfeed.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jskako.rssfeed.domain.mapper.toRssChannel
import com.jskako.rssfeed.domain.mapper.toRssItems
import com.jskako.rssfeed.domain.usecase.rss.api.ApiUseCases
import com.jskako.rssfeed.domain.usecase.rss.database.DatabaseChannelUseCases
import com.jskako.rssfeed.presentation.delegate.database.DatabaseDelegate
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class InitViewModel(
    private val apiUseCases: ApiUseCases,
    private val databaseChannelUseCases: DatabaseChannelUseCases,
    private val databaseDelegate: DatabaseDelegate
) : ViewModel() {


    fun fetchRssFeeds(
        onCurrentStepIndex: ((Int) -> Unit)? = null,
        onTotalSteps: ((Int) -> Unit),
        onDone: () -> Unit
    ) = viewModelScope.launch {

        val rssList = databaseChannelUseCases.getRssChannels().first().map { it.rss }

        onTotalSteps(rssList.size)

        rssList.forEachIndexed { index, rss ->
            onCurrentStepIndex?.let {
                it(index.inc())
            }

            runCatching {

                val feeds = apiUseCases.fetchRssFeeds(rss = rss)

                databaseDelegate.addToDatabase(
                    rss = rss,
                    rssChannel = feeds.rssApiChannel.toRssChannel(
                        databaseChannelUseCases = databaseChannelUseCases
                    ),
                    rssItems = feeds.rssApiItems.toRssItems(
                        databaseChannelUseCases = databaseChannelUseCases
                    )
                )
            }.onFailure {
                Log.e("Error", "$it")
            }
        }
        onDone()
    }
}
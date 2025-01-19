package com.jskako.rssfeed.presentation.delegate.worker

import com.jskako.rssfeed.domain.model.database.RssChannel
import com.jskako.rssfeed.domain.model.database.RssItem
import com.jskako.rssfeed.presentation.state.RssWorkerState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import java.time.Instant

interface WorkerDelegate {
    val rssWorkerState: StateFlow<RssWorkerState>
    suspend fun scheduleRssWorker(rss: String)
    suspend fun schedulePeriodicRssWorker(rss: String)
    suspend fun cancelRssWorker(rss: String)
}
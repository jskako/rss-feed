package com.jskako.rssfeed.presentation.delegate.worker

import com.jskako.rssfeed.presentation.state.RssWorkerState
import kotlinx.coroutines.flow.StateFlow

interface WorkerDelegate {
    val rssWorkerState: StateFlow<RssWorkerState>
    suspend fun scheduleRssWorker(rss: String, runRssExistCheck: Boolean)
    suspend fun schedulePeriodicRssWorker(rss: String)
    suspend fun cancelRssWorker(rss: String)
}
package com.jskako.rssfeed.presentation.state

sealed class RssWorkerState {
    data object Idle : RssWorkerState()
    data object Running : RssWorkerState()
    data object Fetched : RssWorkerState()
    data object Scheduled : RssWorkerState()
    data object Unscheduled : RssWorkerState()
}
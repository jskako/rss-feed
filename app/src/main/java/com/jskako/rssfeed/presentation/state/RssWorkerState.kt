package com.jskako.rssfeed.presentation.state

sealed class RssWorkerState {
    data object Idle : RssWorkerState()
    data object Running : RssWorkerState()
    data class FetchDone(val result: Result<String?>) : RssWorkerState()
    data object Scheduled : RssWorkerState()
    data object Unscheduled : RssWorkerState()
}
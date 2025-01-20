package com.jskako.rssfeed.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jskako.rssfeed.presentation.delegate.database.DatabaseDelegate
import com.jskako.rssfeed.presentation.delegate.worker.WorkerDelegate
import com.jskako.rssfeed.presentation.event.RssEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class InitViewModel(
    private val workerDelegate: WorkerDelegate,
    private val databaseDelegate: DatabaseDelegate
) : ViewModel() {

    private val _totalSteps = MutableStateFlow(0)
    val totalSteps: StateFlow<Int> = _totalSteps

    private val _currentStep = MutableStateFlow(0)
    val currentStep: StateFlow<Int> = _currentStep

    fun onRssEvent(event: RssEvent) {
        when (event) {

            is RssEvent.FetchRssFeeds -> {
                viewModelScope.launch {
                    val rssList = databaseDelegate.getRssChannels().first().map { it.rss }
                    _totalSteps.value = rssList.size

                    rssList.forEachIndexed { index, rss ->

                        _currentStep.value = index.inc()

                        workerDelegate.scheduleRssWorker(
                            rss = rss,
                            runRssExistCheck = false
                        )
                    }

                    event.onDone()
                }
            }

            else -> {}
        }
    }
}
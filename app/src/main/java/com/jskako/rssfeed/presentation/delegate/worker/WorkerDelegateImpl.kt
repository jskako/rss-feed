package com.jskako.rssfeed.presentation.delegate.worker

import android.app.Application
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.jskako.rssfeed.core.utils.RSS_CHECK_WORKER_KEY
import com.jskako.rssfeed.core.utils.RSS_WORKER_KEY
import com.jskako.rssfeed.data.worker.RssWorker
import com.jskako.rssfeed.presentation.state.RssWorkerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.concurrent.TimeUnit

class WorkerDelegateImpl(
    private val context: Application
) : WorkerDelegate {

    private val _rssWorkerState = MutableStateFlow<RssWorkerState>(RssWorkerState.Idle)
    override val rssWorkerState: StateFlow<RssWorkerState> = _rssWorkerState

    override suspend fun scheduleRssWorker(rss: String, runRssExistCheck: Boolean) {
        _rssWorkerState.value = RssWorkerState.Running

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val inputData = Data.Builder()
            .putString(RSS_WORKER_KEY, rss)
            .putBoolean(RSS_CHECK_WORKER_KEY, runRssExistCheck)
            .build()

        val rssWorkRequest = OneTimeWorkRequestBuilder<RssWorker>()
            .setInputData(inputData)
            .setConstraints(constraints)
            .addTag(rss)
            .build()

        WorkManager.getInstance(context).enqueue(rssWorkRequest)

        _rssWorkerState.value = RssWorkerState.Fetched
    }

    override suspend fun schedulePeriodicRssWorker(rss: String) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()

        val inputData = Data.Builder()
            .putString(RSS_WORKER_KEY, rss)
            .build()

        val rssPeriodicWorkRequest = PeriodicWorkRequestBuilder<RssWorker>(
            1, TimeUnit.HOURS
        )
            .setInputData(inputData)
            .setConstraints(constraints)
            .addTag(rss)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            rss,
            ExistingPeriodicWorkPolicy.KEEP,
            rssPeriodicWorkRequest
        )

        _rssWorkerState.value = RssWorkerState.Scheduled
    }

    override suspend fun cancelRssWorker(rss: String) {
        WorkManager.getInstance(context).cancelAllWorkByTag(rss)
        _rssWorkerState.value = RssWorkerState.Unscheduled
    }


}

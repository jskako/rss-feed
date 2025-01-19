package com.jskako.rssfeed.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.jskako.rssfeed.core.utils.RSS_WORKER_KEY
import com.jskako.rssfeed.domain.mapper.toRssChannel
import com.jskako.rssfeed.domain.mapper.toRssItem
import com.jskako.rssfeed.domain.usecase.rss.api.ApiUseCases
import com.jskako.rssfeed.presentation.delegate.database.DatabaseDelegate
import kotlinx.coroutines.flow.first

class RssWorker(
    context: Context,
    params: WorkerParameters,
    private val apiUseCases: ApiUseCases,
    private val databaseDelegate: DatabaseDelegate,
    //private val notificationSender: NotificationSender
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val rss = inputData.getString(RSS_WORKER_KEY) ?: return Result.failure()

        return runCatching {

            val feeds = apiUseCases.fetchRssFeeds(rss = rss)

            val rssChannel = feeds.rssApiChannel.toRssChannel(
                isNotificationEnabled = databaseDelegate.isNotificationEnabled(rss = rss)
            )

            databaseDelegate.addToDatabase(
                rss = rss,
                rssChannel = rssChannel,
                rssItems = feeds.rssApiItems.map { item ->
                    item.toRssItem(
                        hasBeenRead = databaseDelegate.hasBeenRead(guid = item.guid),
                        isFavorite = databaseDelegate.isFavorite(guid = item.guid)
                    )
                }
            )

            val unreadItems = databaseDelegate.getUnreadItemsCount(rss = rss).first()

            /*if (unreadItems > 0) {
                notificationSender.sendNotification(
                    title = context.getString(R.string.rss_notification_title),
                    text = context.getString(R.string.rss_notification_desc, unreadItems)
                )
            }*/
        }.fold(
            onSuccess = {
                Result.success()
            },
            onFailure = {
                Result.failure()
            }
        )
    }
}

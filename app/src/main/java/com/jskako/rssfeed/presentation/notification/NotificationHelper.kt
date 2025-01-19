package com.jskako.rssfeed.presentation.notification

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.jskako.rssfeed.R
import com.jskako.rssfeed.domain.notifications.NotificationSender

class NotificationHelper(private val context: Context) : NotificationSender {

    override fun sendNotification(
        title: String,
        text: String
    ) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationId = System.currentTimeMillis().toInt()
        val notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_NAME)
            .setSmallIcon(R.drawable.rss_logo)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(notificationId, notification)
    }
}

private const val NOTIFICATION_CHANNEL_NAME = "RSS_CHANNEL"

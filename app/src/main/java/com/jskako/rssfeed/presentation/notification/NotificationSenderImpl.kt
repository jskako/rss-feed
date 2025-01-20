package com.jskako.rssfeed.presentation.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.jskako.rssfeed.R
import com.jskako.rssfeed.domain.notifications.NotificationSender

class NotificationSenderImpl(private val context: Context) : NotificationSender {

    override fun sendNotification(
        title: String,
        text: String
    ) {
        createNotificationChannel()

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

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_NAME,
            context.getString(R.string.rss_notification_title),
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = context.getString(R.string.rss_notification_desc)
        }

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

private const val NOTIFICATION_CHANNEL_NAME = "RSS_CHANNEL"

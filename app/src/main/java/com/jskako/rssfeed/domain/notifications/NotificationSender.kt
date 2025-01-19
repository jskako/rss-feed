package com.jskako.rssfeed.domain.notifications

interface NotificationSender {
    fun sendNotification(title: String, text: String)
}
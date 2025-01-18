package com.jskako.rssfeed.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jskako.rssfeed.data.local.dao.RssChannelDao
import com.jskako.rssfeed.data.local.dao.RssItemDao
import com.jskako.rssfeed.data.local.models.RssChannelEntity
import com.jskako.rssfeed.data.local.models.RssItemEntity

@Database(
    entities = [
        RssChannelEntity::class,
        RssItemEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun rssChannelDao(): RssChannelDao
    abstract fun rssItemDao(): RssItemDao

    companion object {
        const val DATABASE_NAME = "rss_feed_db"
    }
}
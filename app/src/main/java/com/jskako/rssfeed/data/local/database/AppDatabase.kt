package com.jskako.rssfeed.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jskako.rssfeed.data.local.dao.RssDao
import com.jskako.rssfeed.data.model.RssEntity

@Database(
    entities = [RssEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun rssDao(): RssDao

    companion object {
        const val DATABASE_NAME = "rss_feed_db"
    }
}
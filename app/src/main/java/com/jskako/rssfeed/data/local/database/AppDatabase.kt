package com.jskako.rssfeed.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jskako.rssfeed.data.local.dao.RssEntityDao
import com.jskako.rssfeed.data.local.models.RssEntity
import com.jskako.rssfeed.data.local.models.RssItemEntity

@Database(
    entities = [RssEntity::class, RssItemEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun rssDao(): RssEntityDao

    companion object {
        const val DATABASE_NAME = "rss_feed_db"
    }
}
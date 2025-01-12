package com.jskako.rssfeed.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jskako.rssfeed.data.local.dao.RssDao
import com.jskako.rssfeed.data.model.RssEntity
import com.jskako.rssfeed.data.model.RssItemEntity

@Database(
    entities = [RssEntity::class, RssItemEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun rssDao(): RssDao

    companion object {
        const val DATABASE_NAME = "rss_feed_db"
    }
}
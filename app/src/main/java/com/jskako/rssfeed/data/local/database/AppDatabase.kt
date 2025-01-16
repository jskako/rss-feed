package com.jskako.rssfeed.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jskako.rssfeed.data.local.dao.RssChanelEntityDao
import com.jskako.rssfeed.data.local.dao.RssChanelInfoEntityDao
import com.jskako.rssfeed.data.local.models.RssChannelEntity
import com.jskako.rssfeed.data.local.models.RssChannelInfoEntity
import com.jskako.rssfeed.data.local.models.RssItemEntity
import com.jskako.rssfeed.data.local.models.RssItemInfoEntity

@Database(
    entities = [
        RssChannelEntity::class,
        RssItemEntity::class,
        RssItemInfoEntity::class,
        RssChannelInfoEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun rssDao(): RssChanelEntityDao
    abstract fun rssInfoDao(): RssChanelInfoEntityDao

    companion object {
        const val DATABASE_NAME = "rss_feed_db"
    }
}
package com.jskako.rssfeed.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jskako.rssfeed.data.local.models.RssChannelInfoEntity

@Dao
interface RssChanelInfoEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertRssChannelInfo(rssChannelInfo: RssChannelInfoEntity)

    @Query("SELECT isNotificationEnabled FROM rss_channel_info WHERE rss = :rss")
    suspend fun isNotificationEnabled(rss: String): Boolean
}
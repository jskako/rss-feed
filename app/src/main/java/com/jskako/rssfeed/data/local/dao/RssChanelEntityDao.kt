package com.jskako.rssfeed.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jskako.rssfeed.data.local.models.RssChannelEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RssChanelEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertRss(rss: RssChannelEntity)

    @Query("SELECT * FROM rss_channel")
    fun getAll(): Flow<List<RssChannelEntity>>

    @Query("DELETE FROM rss_channel WHERE rss = :rss")
    suspend fun deleteByUrl(rss: String)

    @Query("SELECT * FROM rss_channel WHERE rss = :rss")
    suspend fun get(rss: String): RssChannelEntity?

    @Query("SELECT last_build_date FROM rss_channel WHERE rss = :rss")
    suspend fun getLastBuildDate(rss: String): String?

    @Query("SELECT EXISTS(SELECT 1 FROM rss_channel WHERE rss = :rss)")
    suspend fun channelExist(rss: String): Boolean
}
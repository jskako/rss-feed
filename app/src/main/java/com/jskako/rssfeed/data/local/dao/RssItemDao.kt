package com.jskako.rssfeed.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jskako.rssfeed.data.local.models.RssItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RssItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(rssItem: RssItemEntity)

    @Query("SELECT * FROM rss_item WHERE rss = :rss")
    fun getItemsByRss(rss: String): Flow<List<RssItemEntity>>

    @Query("SELECT * FROM rss_item WHERE guid = :guid")
    suspend fun getByGuid(guid: String): RssItemEntity?

    @Query("SELECT update_date FROM rss_item WHERE guid = :guid ORDER BY update_date DESC LIMIT 1")
    suspend fun getLastUpdateDate(guid: String): String?

    @Query("SELECT EXISTS(SELECT 1 FROM rss_item WHERE guid = :guid)")
    suspend fun itemExists(guid: String): Boolean

    @Query("SELECT COUNT(*) FROM rss_item WHERE rss = :rss AND has_been_read = 0")
    suspend fun countUnreadItems(rss: String): Int

    @Query("UPDATE rss_item SET has_been_read = :isEnabled WHERE guid = :guid")
    suspend fun updateReadStatus(guid: String, isEnabled: Boolean)

    @Query("SELECT is_favorite FROM rss_item WHERE guid = :guid")
    suspend fun isFavorite(guid: String): Boolean

    @Query("SELECT has_been_read FROM rss_item WHERE guid = :guid")
    suspend fun hasBeenRead(guid: String): Boolean

    @Query("UPDATE rss_item SET is_favorite = :isEnabled WHERE guid = :guid")
    suspend fun updateFavorite(guid: String, isEnabled: Boolean)
}

package com.jskako.rssfeed.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jskako.rssfeed.data.model.RssEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RssDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRss(rss: RssEntity)

    @Query("SELECT * FROM rss")
    fun getAll(): Flow<List<RssEntity>>

    @Query("DELETE FROM rss WHERE rss = :rss")
    suspend fun deleteByUrl(rss: String)
}
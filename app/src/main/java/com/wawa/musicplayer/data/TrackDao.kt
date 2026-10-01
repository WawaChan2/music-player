package com.wawa.musicplayer.data

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import androidx.room3.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insertTrack(track: Track)

  @Update
  suspend fun updateTrack(track: Track)

  @Delete
  suspend fun deleteTrack(track: Track)

  @Query("SELECT * FROM tracks")
  fun getAllTracks(): Flow<List<Track>>
}
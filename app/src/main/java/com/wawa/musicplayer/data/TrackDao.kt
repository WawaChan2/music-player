package com.wawa.musicplayer.data

import androidx.room3.Dao
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {
  @Query("SELECT * FROM tracks")
  fun getAllTracks(): Flow<List<Track>>
}
package com.wawa.musicplayer.data

import androidx.room3.Database
import androidx.room3.RoomDatabase

@Database(entities = [Track::class], version = 1)
abstract class MusicPlayerDatabase : RoomDatabase() {
  abstract fun trackDao(): TrackDao
}
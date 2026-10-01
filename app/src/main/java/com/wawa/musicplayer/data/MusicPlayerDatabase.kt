package com.wawa.musicplayer.data

import android.content.Context
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase

@Database(entities = [Track::class], version = 1)
abstract class MusicPlayerDatabase : RoomDatabase() {
  abstract fun trackDao(): TrackDao

  companion object {
    @Volatile
    private var Instance: MusicPlayerDatabase? = null

    fun getDatabase(context: Context): MusicPlayerDatabase {
      return Instance ?: synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          MusicPlayerDatabase::class.java,
          "music_player_database"
        ).build()
        Instance = instance
        instance
      }
    }
  }
}
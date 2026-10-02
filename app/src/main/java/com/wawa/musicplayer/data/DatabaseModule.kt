package com.wawa.musicplayer.data

import android.content.Context
import androidx.room3.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

  @Provides
  @Singleton
  fun provideDatabase(
    @ApplicationContext context: Context
  ): MusicPlayerDatabase {
    return Room.databaseBuilder(
      context,
      MusicPlayerDatabase::class.java,
      "music_player_database"
    ).build()
  }

  @Provides
  fun provideTrackDao(database: MusicPlayerDatabase): TrackDao {
    return database.trackDao()
  }
}

package com.wawa.musicplayer.data

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey


@Entity(tableName = "tracks")
data class Track(
  @PrimaryKey(autoGenerate = true)
  val id: Int = 0,

  @ColumnInfo(name = "track_title")
  val trackTitle: String,

  @ColumnInfo(name = "artist_name")
  val artistName: String,

  @ColumnInfo(name = "lyrics")
  val lyrics: String?,

  @ColumnInfo(name = "image_file_path")
  val imageFilePath: String?,

  @ColumnInfo(name = "audio_file_path")
  val audioFilePath: String
)

package com.wawa.musicplayer.ui.screen.upload

import android.graphics.Bitmap
import android.net.Uri
import com.wawa.musicplayer.data.Track

data class UploadState(
  val uploadProcessingState: UploadProcessingState? = null,
  val saveState: SaveState? = null,
  val trackTitle: String = "",
  val artistName: String = "",
  val lyrics: String = "",
  val bitmap: Bitmap? = null,
  val audioUri: Uri? = null,
  val audioFileExtension: String? = null
)

fun UploadState.convertToTrack(imageFilePath: String?, audioFilePath: String): Track {
  return Track(
    trackTitle = trackTitle,
    artistName = artistName,
    lyrics = lyrics.ifBlank { null },
    imageFilePath = imageFilePath,
    audioFilePath = audioFilePath
  )
}

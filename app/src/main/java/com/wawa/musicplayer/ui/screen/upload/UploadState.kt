package com.wawa.musicplayer.ui.screen.upload

import android.graphics.Bitmap
import android.net.Uri
import com.wawa.musicplayer.data.Track

data class UploadState(
  val uploadProcessingState: UploadProcessingState? = null,
  val trackTitle: String = "",
  val artistName: String = "",
  val lyrics: String = "",
  val bitmap: Bitmap? = null,
  val audioUri: Uri? = null
)

fun UploadState.convertToTrack(): Track {
  return Track(
    trackTitle = trackTitle,
    artistName = artistName,
    lyrics = lyrics.ifBlank { null },
    imageFilePath = null,
    audioFilePath = ""
  )
}

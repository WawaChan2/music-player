package com.wawa.musicplayer.ui.screen.upload

import android.graphics.Bitmap

data class UploadState(
  val uploadProcessingState: UploadProcessingState? = null,
  val trackTitle: String = "",
  val artistName: String = "",
  val lyrics: String = "",
  val bitmap: Bitmap? = null
)

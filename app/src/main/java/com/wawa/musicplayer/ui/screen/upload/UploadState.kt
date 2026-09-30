package com.wawa.musicplayer.ui.screen.upload

data class UploadState(
  val uploadProcessingState: UploadProcessingState? = null,
  val trackTitle: String = "",
  val artistName: String = "",
  val lyrics: String = "",
)

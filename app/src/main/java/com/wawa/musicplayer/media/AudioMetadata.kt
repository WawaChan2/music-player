package com.wawa.musicplayer.media

import android.content.Context
import android.media.MediaMetadataRetriever
import android.net.Uri

data class AudioMetadata(
  val trackTitle: String,
  val artistName: String
)

fun getAudioMetadata(context: Context, uri: Uri): AudioMetadata {
  val retriever = MediaMetadataRetriever()

  return try {
    retriever.setDataSource(context, uri)
    AudioMetadata(
      trackTitle = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
        ?: "Unknown",
      artistName = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
        ?: "Unknown"
    )
  } catch (_: Exception) {
    AudioMetadata(
      trackTitle = "Unknown",
      artistName = "Unknown"
    )
  } finally {
    retriever.release()
  }
}

package com.wawa.musicplayer.media

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri

data class AudioMetadata(
  val trackTitle: String,
  val artistName: String,
  val bitmap: Bitmap?
)

fun getAudioMetadata(context: Context, uri: Uri): AudioMetadata? {
  val retriever = MediaMetadataRetriever()

  return try {
    retriever.setDataSource(context, uri)
    val imageByteArray = retriever.embeddedPicture

    AudioMetadata(
      trackTitle = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
        ?: "Unknown",
      artistName = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
        ?: "Unknown",
      bitmap = imageByteArray?.let { BitmapFactory.decodeByteArray(it, 0, it.size) }
    )
  } catch (_: Exception) {
    null
  } finally {
    retriever.release()
  }
}

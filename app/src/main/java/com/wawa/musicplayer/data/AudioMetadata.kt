package com.wawa.musicplayer.data

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.webkit.MimeTypeMap

data class AudioMetadata(
  val trackTitle: String,
  val artistName: String,
  val bitmap: Bitmap?,
  val audioFileExtension: String
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
      bitmap = imageByteArray?.let { BitmapFactory.decodeByteArray(it, 0, it.size) },
      audioFileExtension = getAudioFileExtension(context, uri)
    )
  } catch (_: Exception) {
    null
  } finally {
    retriever.release()
  }
}

fun getBitmap(context: Context, uri: Uri): Bitmap? {
  return try {
    val inputStream = context.contentResolver.openInputStream(uri)
    val bitmap = BitmapFactory.decodeStream(inputStream)

    inputStream?.close()
    bitmap
  } catch (_: Exception) {
    null
  }
}

private fun getAudioFileExtension(context: Context, uri: Uri): String {
  return if (uri.scheme == ContentResolver.SCHEME_CONTENT) {
    val mime = MimeTypeMap.getSingleton()
    mime.getExtensionFromMimeType(context.contentResolver.getType(uri))!!
  } else {
    MimeTypeMap.getFileExtensionFromUrl(uri.toString())
  }
}

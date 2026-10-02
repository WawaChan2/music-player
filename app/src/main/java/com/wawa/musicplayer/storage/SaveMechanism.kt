package com.wawa.musicplayer.storage

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

data class SavedFiles(
  val audioFile: File,
  val imageFile: File? = null
)

fun saveFiles(
  context: Context,
  bitmap: Bitmap?,
  audioUri: Uri,
  imageFileName: String?,
  audioFileName: String
): SavedFiles {
  val audioFile = File(context.filesDir, audioFileName)
  var imageFile: File? = null

  try {
    saveAudio(context, audioUri, audioFile)

    if (bitmap != null && imageFileName != null) {
      imageFile = File(context.filesDir, imageFileName)

      saveBitmap(bitmap, imageFile)
    }

    return SavedFiles(
      audioFile,
      imageFile
    )
  } catch (e: IOException) {
    audioFile.delete()
    imageFile?.delete()

    throw e
  }
}

private fun saveBitmap(
  bitmap: Bitmap,
  file: File
) {
  FileOutputStream(file).use { outputStream ->
    if (!bitmap.compress(
        Bitmap.CompressFormat.JPEG,
        90,
        outputStream
      )
    ) {
      throw IOException("Failed to compress bitmap")
    }
  }
}

private fun saveAudio(
  context: Context,
  uri: Uri,
  file: File
) {
  val inputStream = context.contentResolver.openInputStream(uri)
    ?: throw IOException("Could not open audio URI")

  inputStream.use { input ->
    file.outputStream().use { output ->
      input.copyTo(output)
    }
  }
}
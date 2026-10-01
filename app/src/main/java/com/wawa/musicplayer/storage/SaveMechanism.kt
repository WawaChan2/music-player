package com.wawa.musicplayer.storage

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

fun saveFiles(
  context: Context,
  bitmap: Bitmap,
  audioUri: Uri,
  imageFileName: String,
  audioFileName: String
): List<File> {
  val imageFile = File(context.filesDir, imageFileName)
  val audioFile = File(context.filesDir, audioFileName)

  try {
    saveBitmap(context, bitmap, imageFileName)
    saveAudio(context, audioUri, audioFileName)

    return listOf(imageFile, audioFile)
  } catch (e: IOException) {
    imageFile.delete()
    audioFile.delete()

    throw e
  }
}

private fun saveBitmap(
  context: Context,
  bitmap: Bitmap,
  fileName: String
): File {
  val file = File(context.filesDir, fileName)

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

  return file
}

private fun saveAudio(
  context: Context,
  uri: Uri,
  fileName: String
): File {
  val file = File(context.filesDir, fileName)

  val inputStream = context.contentResolver.openInputStream(uri)
    ?: throw IOException("Could not open audio URI")

  inputStream.use { input ->
    file.outputStream().use { output ->
      input.copyTo(output)
    }
  }

  return file
}
package com.wawa.musicplayer.ui.screen.upload

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wawa.musicplayer.data.AudioMetadata
import com.wawa.musicplayer.data.PlaylistRepository
import com.wawa.musicplayer.storage.SavedFiles
import com.wawa.musicplayer.storage.saveFiles
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class UploadViewModel @Inject constructor(
  @ApplicationContext private val context: Context,
  private val playlistRepository: PlaylistRepository
) : ViewModel() {
  private val _uploadState = MutableStateFlow(UploadState())
  val uploadState: StateFlow<UploadState> = _uploadState.asStateFlow()

  fun saveTrack() {
    setSaveState(SaveLoading)

    viewModelScope.launch {
      val currentState = _uploadState.value
      var savedFiles: SavedFiles? = null
      val trackId = UUID.randomUUID().toString()

      try {
        savedFiles = saveFiles(
          context = context,
          bitmap = currentState.bitmap,
          audioUri = currentState.audioUri!!,
          imageFileName = "image_${trackId}.jpg",
          audioFileName = "audio_${trackId}.${currentState.audioFileExtension!!}"
        )

        val trackFormat = currentState.convertToTrack(
          imageFilePath = savedFiles.imageFile?.absolutePath,
          audioFilePath = savedFiles.audioFile.absolutePath
        )

        playlistRepository.insertTrack(trackFormat)

        setSaveState(SaveSuccess)
      } catch (_: Exception) {
        savedFiles?.audioFile?.delete()
        savedFiles?.imageFile?.delete()

        setSaveState(SaveError)
      }
    }
  }

  fun updateUploadForm(audioMetadata: AudioMetadata, audioUri: Uri) {
    setTrackTitle(audioMetadata.trackTitle)
    setArtistName(audioMetadata.artistName)
    setLyrics("")
    setBitmap(audioMetadata.bitmap)
    setAudioUri(audioUri)
    setAudioFileExtension(audioMetadata.audioFileExtension)

    setUploadProcessingState(UploadSuccess)
  }

  fun setUploadProcessingState(uploadProcessingState: UploadProcessingState?) {
    _uploadState.update { currentState ->
      currentState.copy(uploadProcessingState = uploadProcessingState)
    }
  }

  fun setTrackTitle(newTrackTitle: String) {
    _uploadState.update { currentState ->
      currentState.copy(trackTitle = newTrackTitle)
    }
  }

  fun setArtistName(newArtistName: String) {
    _uploadState.update { currentState ->
      currentState.copy(artistName = newArtistName)
    }
  }

  fun setLyrics(newLyrics: String) {
    _uploadState.update { currentState ->
      currentState.copy(lyrics = newLyrics)
    }
  }

  fun setBitmap(bitmap: Bitmap?) {
    _uploadState.update { currentState ->
      currentState.copy(bitmap = bitmap)
    }
  }

  fun setAudioUri(audioUri: Uri) {
    _uploadState.update { currentState ->
      currentState.copy(audioUri = audioUri)
    }
  }

  fun setAudioFileExtension(audioFileExtension: String) {
    _uploadState.update { currentState ->
      currentState.copy(audioFileExtension = audioFileExtension)
    }
  }

  fun setSaveState(saveState: SaveState?) {
    _uploadState.update { currentState ->
      currentState.copy(saveState = saveState)
    }
  }

  fun onTrackTitleChange(newTrackTitle: String) {
    setTrackTitle(newTrackTitle)
  }

  fun onArtistNameChange(newArtistName: String) {
    setArtistName(newArtistName)
  }

  fun onLyricsChange(newLyrics: String) {
    setLyrics(newLyrics)
  }
}
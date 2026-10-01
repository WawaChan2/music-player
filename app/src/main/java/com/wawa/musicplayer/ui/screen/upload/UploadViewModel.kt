package com.wawa.musicplayer.ui.screen.upload

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.wawa.musicplayer.data.AudioMetadata
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UploadViewModel : ViewModel() {
  private val _uploadState = MutableStateFlow(UploadState())
  val uploadState: StateFlow<UploadState> = _uploadState.asStateFlow()

  fun updateUploadForm(audioMetadata: AudioMetadata, audioUri: Uri) {
    setTrackTitle(audioMetadata.trackTitle)
    setArtistName(audioMetadata.artistName)
    setLyrics("")
    setBitmap(audioMetadata.bitmap)
    setAudioUri(audioUri)

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
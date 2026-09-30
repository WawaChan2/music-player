package com.wawa.musicplayer.ui.screen.upload

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UploadViewModel : ViewModel() {
  private val _uploadState = MutableStateFlow(UploadState())
  val uploadState: StateFlow<UploadState> = _uploadState.asStateFlow()

  fun setUploadProcessingState(uploadProcessingState: UploadProcessingState) {
    _uploadState.update { currentState ->
      currentState.copy(uploadProcessingState = uploadProcessingState)
    }
  }

  fun onTrackTitleChange(newTrackTitle: String) {
    _uploadState.update { currentState ->
      currentState.copy(trackTitle = newTrackTitle)
    }
  }

  fun onArtistNameChange(newArtistName: String) {
    _uploadState.update { currentState ->
      currentState.copy(artistName = newArtistName)
    }
  }

  fun onLyricsChange(newLyrics: String) {
    _uploadState.update { currentState ->
      currentState.copy(lyrics = newLyrics)
    }
  }
}
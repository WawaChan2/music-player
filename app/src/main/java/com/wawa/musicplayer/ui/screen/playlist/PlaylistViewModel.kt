package com.wawa.musicplayer.ui.screen.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wawa.musicplayer.data.PlaylistRepository
import com.wawa.musicplayer.data.Track
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaylistViewModel @Inject constructor(private val playlistRepository: PlaylistRepository) : ViewModel() {
  val playlistUiState: StateFlow<PlaylistUiState> = playlistRepository.getAllTracks()
    .map { PlaylistUiState(it) }
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = PlaylistUiState()
    )

  fun insertTrack(track: Track) {
    viewModelScope.launch {
      playlistRepository.insertTrack(track)
    }
  }

  fun updateTrack(track: Track) {
    viewModelScope.launch {
      playlistRepository.updateTrack(track)
    }
  }

  fun deleteTrack(track: Track) {
    viewModelScope.launch {
      playlistRepository.deleteTrack(track)
    }
  }
}
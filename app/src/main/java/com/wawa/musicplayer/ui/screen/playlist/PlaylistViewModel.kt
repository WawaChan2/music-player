package com.wawa.musicplayer.ui.screen.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wawa.musicplayer.data.PlaylistRepository
import com.wawa.musicplayer.data.Track
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaylistViewModel @Inject constructor(private val playlistRepository: PlaylistRepository) :
  ViewModel() {
  private val selectedTrackIdFlow = MutableStateFlow<Int?>(null)

  val playlistUiState: StateFlow<PlaylistUiState> = combine(
    selectedTrackIdFlow,
    playlistRepository.getAllTracks()
  ) { selectedTrackId, allTracks ->
    PlaylistUiState(
      selectedTrackId = selectedTrackId,
      allTracks = allTracks
    )
  }.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000),
    initialValue = PlaylistUiState()
  )

  fun selectTrackById(id: Int) {
    selectedTrackIdFlow.update { id }
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
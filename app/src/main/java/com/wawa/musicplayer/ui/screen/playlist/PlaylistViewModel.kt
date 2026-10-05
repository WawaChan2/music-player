package com.wawa.musicplayer.ui.screen.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wawa.musicplayer.R
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
  private val selectedTrackIdOnDisplayFlow = MutableStateFlow<Int?>(null)
  private val selectedTrackIdOnEditorFlow = MutableStateFlow<Int?>(null)
  private val selectedOptionIndexFlow = MutableStateFlow(0)
  private val optionsFlow = MutableStateFlow(
    listOf(
      R.drawable.play_arrow_24px,
      R.drawable.edit_24px,
      R.drawable.delete_24px
    )
  )

  val playlistUiState: StateFlow<PlaylistUiState> = combine(
    selectedTrackIdOnDisplayFlow,
    selectedTrackIdOnEditorFlow,
    playlistRepository.getAllTracks(),
    selectedOptionIndexFlow,
    optionsFlow
  ) { selectedTrackIdOnDisplay, selectedTrackIdOnEditor, allTracks, selectedOptionIndex, options ->
    PlaylistUiState(
      selectedTrackIdOnDisplay = selectedTrackIdOnDisplay,
      selectedTrackIdOnEditor = selectedTrackIdOnEditor,
      allTracks = allTracks,
      selectedOptionIndex = selectedOptionIndex,
      options = options
    )
  }.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000),
    initialValue = PlaylistUiState()
  )

  fun selectTrackByIdOnDisplay(id: Int?) {
    selectedTrackIdOnDisplayFlow.update { id }
  }

  fun selectTrackByIdOnEditor(id: Int?) {
    selectedTrackIdOnEditorFlow.update { id }
  }

  fun selectOptionIndex(index: Int) {
    selectedOptionIndexFlow.update { index }
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
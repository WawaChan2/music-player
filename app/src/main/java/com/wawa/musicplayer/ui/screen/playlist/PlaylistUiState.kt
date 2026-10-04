package com.wawa.musicplayer.ui.screen.playlist

import com.wawa.musicplayer.data.Track

data class PlaylistUiState(
  val selectedTrackIdOnDisplay: Int? = null,
  val selectedTrackIdOnEditor: Int? = null,
  val allTracks: List<Track> = emptyList()
)

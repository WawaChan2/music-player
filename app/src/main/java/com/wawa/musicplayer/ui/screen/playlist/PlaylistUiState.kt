package com.wawa.musicplayer.ui.screen.playlist

import com.wawa.musicplayer.data.Track

data class PlaylistUiState(
  val allTracks: List<Track> = emptyList()
)

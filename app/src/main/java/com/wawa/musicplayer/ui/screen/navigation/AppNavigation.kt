package com.wawa.musicplayer.ui.screen.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wawa.musicplayer.ui.screen.playlist.PlaylistScreen
import com.wawa.musicplayer.ui.screen.playlist.PlaylistUiState
import com.wawa.musicplayer.ui.screen.upload.UploadScreen
import com.wawa.musicplayer.ui.screen.upload.UploadState

@Composable
fun AppNavigation(
  windowSizeClass: WindowSizeClass,
  navigationState: NavigationState,
  uploadState: UploadState,
  playlistUiState: PlaylistUiState,
  onNavigateBack: () -> Unit,
  onTrackTitleTextFieldChange: (String) -> Unit,
  onArtistNameTextFieldChange: (String) -> Unit,
  onLyricsTextFieldChange: (String) -> Unit,
  onEditIconClick: () -> Unit,
  onUploadAudioButtonClick: () -> Unit,
  onSaveButtonClick: () -> Unit,
  onCancelButtonClick: () -> Unit,
  onDialogClose: () -> Unit,
  onItemClick: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
  when (navigationState.selectedTab) {
    PlaylistTab -> PlaylistScreen(
      windowSizeClass = windowSizeClass,
      navigationState = navigationState,
      playlistUiState = playlistUiState,
      onNavigateBack = onNavigateBack,
      onItemClick = onItemClick,
      modifier = modifier
        .fillMaxSize()
    )

    UploadTab -> UploadScreen(
      windowSizeClass = windowSizeClass,
      uploadState = uploadState,
      onTrackTitleTextFieldChange = onTrackTitleTextFieldChange,
      onArtistNameTextFieldChange = onArtistNameTextFieldChange,
      onLyricsTextFieldChange = onLyricsTextFieldChange,
      onEditIconClick = onEditIconClick,
      onUploadAudioButtonClick = onUploadAudioButtonClick,
      onSaveButtonClick = onSaveButtonClick,
      onCancelButtonClick = onCancelButtonClick,
      onDialogClose = onDialogClose,
      modifier = modifier
        .fillMaxSize()
        .padding(start = 16.dp, end = 16.dp)
    )
  }
}
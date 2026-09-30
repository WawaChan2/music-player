package com.wawa.musicplayer.ui.screen.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wawa.musicplayer.ui.screen.playlist.PlaylistScreen
import com.wawa.musicplayer.ui.screen.upload.UploadScreen
import com.wawa.musicplayer.ui.screen.upload.UploadState

@Composable
fun AppNavigation(
  windowSizeClass: WindowSizeClass,
  navigationState: NavigationState,
  onNavigateToPlayer: () -> Unit,
  onNavigateBack: () -> Unit,
  uploadState: UploadState,
  onTrackTitleTextFieldChange: (String) -> Unit,
  onArtistNameTextFieldChange: (String) -> Unit,
  onLyricsTextFieldChange: (String) -> Unit,
  uploadAudioButtonOnClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  when (navigationState.selectedTab) {
    PlaylistTab -> PlaylistScreen(
      windowSizeClass = windowSizeClass,
      navigationState = navigationState,
      onNavigateToPlayer = onNavigateToPlayer,
      onNavigateBack = onNavigateBack,
      modifier = modifier
        .fillMaxSize()
        .padding(top = 8.dp, end = 16.dp, bottom = 16.dp, start = 16.dp)
    )

    UploadTab -> UploadScreen(
      windowSizeClass = windowSizeClass,
      uploadState = uploadState,
      onTrackTitleTextFieldChange = onTrackTitleTextFieldChange,
      onArtistNameTextFieldChange = onArtistNameTextFieldChange,
      onLyricsTextFieldChange = onLyricsTextFieldChange,
      uploadAudioButtonOnClick = uploadAudioButtonOnClick,
      modifier = modifier
        .fillMaxSize()
        .padding(top = 8.dp, end = 16.dp, bottom = 16.dp, start = 16.dp)
    )
  }
}
package com.wawa.musicplayer.ui.screen.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wawa.musicplayer.ui.screen.playlist.PlaylistScreen
import com.wawa.musicplayer.ui.screen.upload.UploadScreen

@Composable
fun AppNavigation(
  navigationState: NavigationState,
  onNavigateToPlayer: () -> Unit,
  onNavigateBack: () -> Unit,
  modifier: Modifier = Modifier
) {
  when (navigationState.selectedTab) {
    PlaylistTab -> PlaylistScreen(
      navigationState = navigationState,
      onNavigateToPlayer = onNavigateToPlayer,
      onNavigateBack = onNavigateBack,
      modifier = modifier
    )

    UploadTab -> UploadScreen(modifier = modifier)
  }
}
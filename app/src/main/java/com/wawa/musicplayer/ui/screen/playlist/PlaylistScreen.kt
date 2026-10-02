package com.wawa.musicplayer.ui.screen.playlist

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wawa.musicplayer.ui.screen.navigation.NavigationState
import com.wawa.musicplayer.ui.screen.navigation.Picker
import com.wawa.musicplayer.ui.screen.navigation.Player

@Composable
fun PlaylistScreen(
  windowSizeClass: WindowSizeClass,
  navigationState: NavigationState,
  playlistUiState: PlaylistUiState,
  onNavigateToPlayer: () -> Unit,
  onNavigateBack: () -> Unit,
  modifier: Modifier = Modifier
) {
  val currentScreen = navigationState.backStackByTab[navigationState.selectedTab]!!.last()

  if (currentScreen == Player) {
    PlayerScreen(
      windowSizeClass = windowSizeClass,
      onNavigateBack = onNavigateBack,
      modifier = modifier
    )
  } else if (currentScreen == Picker) {
    PickerScreen(
      windowSizeClass = windowSizeClass,
      playlistUiState = playlistUiState,
      onNavigateToPlayer = onNavigateToPlayer,
      modifier = modifier
    )
  }
}
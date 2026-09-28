package com.wawa.musicplayer.ui.screen.playlist

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wawa.musicplayer.ui.screen.navigation.NavigationState
import com.wawa.musicplayer.ui.screen.navigation.Picker
import com.wawa.musicplayer.ui.screen.navigation.Player

@Composable
fun PlaylistScreen(
  navigationState: NavigationState,
  onNavigateToPlayer: () -> Unit,
  onNavigateBack: () -> Unit,
  modifier: Modifier = Modifier
) {
  val currentScreen = navigationState.backStackByTab[navigationState.selectedTab]!!.last()

  if (currentScreen == Player) {
    PlayerScreen(
      onNavigateBack = onNavigateBack,
      modifier = modifier
    )
  } else if (currentScreen == Picker) {
    PickerScreen(
      onNavigateToPlayer = onNavigateToPlayer,
      modifier = modifier
    )
  }
}
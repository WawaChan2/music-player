package com.wawa.musicplayer.ui.screen.playlist

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wawa.musicplayer.ui.screen.navigation.Display
import com.wawa.musicplayer.ui.screen.navigation.Editor
import com.wawa.musicplayer.ui.screen.navigation.NavigationState
import com.wawa.musicplayer.ui.screen.navigation.NowPlaying

@Composable
fun PlaylistScreen(
  windowSizeClass: WindowSizeClass,
  navigationState: NavigationState,
  playlistUiState: PlaylistUiState,
  onNavigateBack: () -> Unit,
  onItemClick: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
  val currentScreen = navigationState.backStackByTab[navigationState.selectedTab]!!.last()

  when (currentScreen) {
    Display -> {
      DisplayScreen(
        windowSizeClass = windowSizeClass,
        playlistUiState = playlistUiState,
        onNavigateBack = onNavigateBack,
        onItemClick = onItemClick,
        modifier = modifier
      )
    }

    Editor -> {
      EditorScreen(
        windowSizeClass = windowSizeClass,
        onNavigateBack = onNavigateBack,
        modifier = modifier
      )
    }

    NowPlaying -> {
      NowPlayingScreen(
        windowSizeClass = windowSizeClass,
        onNavigateBack = onNavigateBack,
        modifier = modifier
      )
    }

    else -> Unit
  }
}
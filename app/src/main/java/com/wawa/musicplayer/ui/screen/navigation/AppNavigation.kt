package com.wawa.musicplayer.ui.screen.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.wawa.musicplayer.ui.screen.playlist.PickerScreen
import com.wawa.musicplayer.ui.screen.playlist.PlayerScreen
import com.wawa.musicplayer.ui.screen.upload.UploadScreen

@Composable
fun AppNavigation(
  navigationUiState: NavigationUiState,
  modifier: Modifier = Modifier
) {
  val navController = rememberNavController()

  NavHost(
    navController = navController,
    startDestination = TopLevelDestination.getByLabelId(navigationUiState.labelId)!!.graph
  ) {
    navigation<PlaylistGraph>(startDestination = Picker) {
      composable<Picker> { PickerScreen(modifier = modifier) }
      composable<Player> { PlayerScreen(modifier = modifier) }
    }

    navigation<UploadGraph>(startDestination = Upload) {
      composable<Upload> { UploadScreen(modifier = modifier) }
    }
  }
}
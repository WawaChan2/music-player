package com.wawa.musicplayer.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wawa.musicplayer.R
import com.wawa.musicplayer.ui.screen.AdaptiveLayout
import com.wawa.musicplayer.ui.screen.navigation.NavigationViewModel

@Composable
fun MusicPlayerApp(
  windowSizeClass: WindowSizeClass,
  navigationViewModel: NavigationViewModel = hiltViewModel()
) {
  val navigationUiState by navigationViewModel.navigationUiState.collectAsStateWithLifecycle()

  NavigationSuiteScaffold(
    navigationSuiteItems = {
      AppDestination.entries.forEach { destination ->
        item(
          selected = destination.titleId == navigationUiState.destinationId,
          onClick = { navigationViewModel.setDestination(destination.titleId) },
          icon = {
            Icon(
              imageVector = ImageVector.vectorResource(destination.iconId),
              contentDescription = stringResource(destination.titleId)
            )
          },
          label = {
            Text(text = stringResource(destination.titleId))
          }
        )
      }
    },
    layoutType = when (windowSizeClass.widthSizeClass) {
      WindowWidthSizeClass.Compact -> NavigationSuiteType.NavigationBar
      WindowWidthSizeClass.Medium -> NavigationSuiteType.NavigationRail
      WindowWidthSizeClass.Expanded -> NavigationSuiteType.NavigationDrawer
      else -> NavigationSuiteType.NavigationBar
    }
  ) {
    Scaffold(
      modifier = Modifier.fillMaxSize(),
      topBar = {
        MusicPlayerAppTopBar()
      }
    ) { innerPadding ->
      AdaptiveLayout(
        windowSizeClass = windowSizeClass,
        navigationUiState = navigationUiState,
        modifier = Modifier.padding(innerPadding)
      )
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicPlayerAppTopBar(
  modifier: Modifier = Modifier
) {
  TopAppBar(
    title = {
      Text(text = stringResource(R.string.app_name))
    },
    modifier = modifier
  )
}

enum class AppDestination(@StringRes val titleId: Int, @DrawableRes val iconId: Int) {
  PLAYLIST(titleId = R.string.playlist_nav, iconId = R.drawable.music_note_2_24px),
  UPLOAD(titleId = R.string.upload_nav, iconId = R.drawable.upload_24px)
}
package com.wawa.musicplayer.ui

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
import com.wawa.musicplayer.ui.screen.navigation.AppNavigation
import com.wawa.musicplayer.ui.screen.navigation.NavigationViewModel
import com.wawa.musicplayer.ui.screen.navigation.TopLevelDestination

@Composable
fun MusicPlayerApp(
  windowSizeClass: WindowSizeClass,
  navigationViewModel: NavigationViewModel = hiltViewModel()
) {
  val navigationUiState by navigationViewModel.navigationUiState.collectAsStateWithLifecycle()

  NavigationSuiteScaffold(
    navigationSuiteItems = {
      TopLevelDestination.entries.forEach { destination ->
        item(
          selected = destination.labelId == navigationUiState.labelId,
          onClick = { navigationViewModel.setLabelId(destination.labelId) },
          icon = {
            Icon(
              imageVector = ImageVector.vectorResource(destination.iconId),
              contentDescription = stringResource(destination.labelId)
            )
          },
          label = {
            Text(text = stringResource(destination.labelId))
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
      AppNavigation(
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
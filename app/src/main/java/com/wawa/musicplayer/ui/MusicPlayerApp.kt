package com.wawa.musicplayer.ui

import androidx.annotation.DrawableRes
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.wawa.musicplayer.R
import com.wawa.musicplayer.ui.screen.AdaptiveScreen

@Composable
fun MusicPlayerApp(windowSizeClass: WindowSizeClass) {
  var selectedDestination by remember { mutableStateOf(AppDestination.PLAYLIST) }

  NavigationSuiteScaffold(
    navigationSuiteItems = {
      AppDestination.entries.forEach { destination ->
        item(
          selected = destination == selectedDestination,
          onClick = { selectedDestination = destination },
          icon = {
            Icon(
              imageVector = ImageVector.vectorResource(destination.iconId),
              contentDescription = destination.title
            )
          },
          label = {
            Text(text = destination.title)
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
      AdaptiveScreen(
        windowSizeClass = windowSizeClass,
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

enum class AppDestination(val title: String, @DrawableRes val iconId: Int) {
  PLAYLIST(title = "Playlist", iconId = R.drawable.music_note_2_24px),
  UPLOAD(title = "Upload", iconId = R.drawable.upload_24px)
}
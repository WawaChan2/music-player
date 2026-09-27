package com.wawa.musicplayer.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.wawa.musicplayer.ui.screen.navigation.NavigationUiState

@Composable
fun AdaptiveLayout(
  windowSizeClass: WindowSizeClass,
  navigationUiState: NavigationUiState,
  modifier: Modifier = Modifier
) {
  when (windowSizeClass.widthSizeClass) {
    WindowWidthSizeClass.Compact -> CompactLayout(
      navigationUiState = navigationUiState,
      modifier = modifier
    )

    WindowWidthSizeClass.Medium -> MediumLayout(
      navigationUiState = navigationUiState,
      modifier = modifier
    )

    WindowWidthSizeClass.Expanded -> ExpandedLayout(
      navigationUiState = navigationUiState,
      modifier = modifier
    )

    else -> CompactLayout(
      navigationUiState = navigationUiState,
      modifier = modifier
    )
  }
}

@Composable
fun CompactLayout(
  navigationUiState: NavigationUiState,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    Text(text = "Compact")
  }
}

@Composable
fun MediumLayout(
  navigationUiState: NavigationUiState,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    Text(text = "Medium")
  }
}

@Composable
fun ExpandedLayout(
  navigationUiState: NavigationUiState,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    Text(text = "Expanded")
  }
}
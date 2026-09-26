package com.wawa.musicplayer.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AdaptiveScreen(
  windowSizeClass: WindowSizeClass,
  modifier: Modifier = Modifier
) {
  when (windowSizeClass.widthSizeClass) {
    WindowWidthSizeClass.Compact -> CompactScreen(modifier = modifier)
    WindowWidthSizeClass.Medium -> MediumScreen(modifier = modifier)
    WindowWidthSizeClass.Expanded -> ExpandedScreen(modifier = modifier)
    else -> CompactScreen(modifier = modifier)
  }
}

@Composable
fun CompactScreen(modifier: Modifier = Modifier) {
  Box(
    modifier = modifier
      .background(Color(0xFFFFD000))
      .fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    Text(text = "Compact")
  }
}

@Composable
fun MediumScreen(modifier: Modifier = Modifier) {
  Box(
    modifier = modifier
      .background(Color(0xFFFF9100))
      .fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    Text(text = "Medium")
  }
}

@Composable
fun ExpandedScreen(modifier: Modifier = Modifier) {
  Box(
    modifier = modifier
      .background(Color(0xFFFF3D00))
      .fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    Text(text = "Expanded")
  }
}
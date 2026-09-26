package com.wawa.musicplayer.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.wawa.musicplayer.R
import com.wawa.musicplayer.ui.screen.HomeScreen

@Composable
fun MusicPlayerApp() {
  Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
      MusicPlayerAppTopBar()
    }
  ) { innerPadding ->
    HomeScreen(modifier = Modifier.padding(innerPadding))
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicPlayerAppTopBar(modifier: Modifier = Modifier) {
  CenterAlignedTopAppBar(
    title = {
      Text(text = stringResource(R.string.app_name))
    },
    modifier = modifier
  )
}
package com.wawa.musicplayer.ui.screen.playlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.wawa.musicplayer.R

@Composable
fun DisplayScreen(
  windowSizeClass: WindowSizeClass,
  playlistUiState: PlaylistUiState,
  onNavigateToEditor: () -> Unit,
  modifier: Modifier = Modifier
) {
  when (windowSizeClass.widthSizeClass) {
    WindowWidthSizeClass.Compact -> CompactDisplayScreen(
      playlistUiState = playlistUiState,
      onNavigateToEditor = onNavigateToEditor,
      modifier = modifier
    )

    WindowWidthSizeClass.Medium -> MediumDisplayScreen(
      playlistUiState = playlistUiState,
      onNavigateToEditor = onNavigateToEditor,
      modifier = modifier
    )

    WindowWidthSizeClass.Expanded -> ExpandedDisplayScreen(
      playlistUiState = playlistUiState,
      onNavigateToEditor = onNavigateToEditor,
      modifier = modifier
    )

    else -> CompactDisplayScreen(
      playlistUiState = playlistUiState,
      onNavigateToEditor = onNavigateToEditor,
      modifier = modifier
    )
  }
}

@Composable
fun CompactDisplayScreen(
  playlistUiState: PlaylistUiState,
  onNavigateToEditor: () -> Unit,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    Button(onClick = onNavigateToEditor) {
      Text(text = "Go to editor")
    }
  }
}

@Composable
fun MediumDisplayScreen(
  playlistUiState: PlaylistUiState,
  onNavigateToEditor: () -> Unit,
  modifier: Modifier = Modifier
) {
  TrackList(
    playlistUiState = playlistUiState,
    modifier = modifier
  )
}

@Composable
fun ExpandedDisplayScreen(
  playlistUiState: PlaylistUiState,
  onNavigateToEditor: () -> Unit,
  modifier: Modifier = Modifier
) {
  TrackList(
    playlistUiState = playlistUiState,
    modifier = modifier
  )
}

@Composable
fun TrackList(
  playlistUiState: PlaylistUiState,
  modifier: Modifier = Modifier
) {
  LazyColumn(modifier = modifier) {
    items(playlistUiState.allTracks) { track ->
      AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
          .data(track.imageFilePath)
          .crossfade(true)
          .build(),
        contentDescription = null,
        modifier = Modifier.size(64.dp),
        error = painterResource(R.drawable.image_placeholder),
        contentScale = ContentScale.Crop
      )
    }
  }
}
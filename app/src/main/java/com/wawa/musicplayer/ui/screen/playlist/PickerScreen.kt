package com.wawa.musicplayer.ui.screen.playlist

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
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
fun PickerScreen(
  windowSizeClass: WindowSizeClass,
  playlistUiState: PlaylistUiState,
  onNavigateToPlayer: () -> Unit,
  modifier: Modifier = Modifier
) {
  when (windowSizeClass.widthSizeClass) {
    WindowWidthSizeClass.Compact -> CompactPickerScreen(
      playlistUiState = playlistUiState,
      onNavigateToPlayer = onNavigateToPlayer,
      modifier = modifier
    )

    WindowWidthSizeClass.Medium -> MediumPickerScreen(
      playlistUiState = playlistUiState,
      onNavigateToPlayer = onNavigateToPlayer,
      modifier = modifier
    )

    WindowWidthSizeClass.Expanded -> ExpandedPickerScreen(
      playlistUiState = playlistUiState,
      onNavigateToPlayer = onNavigateToPlayer,
      modifier = modifier
    )

    else -> CompactPickerScreen(
      playlistUiState = playlistUiState,
      onNavigateToPlayer = onNavigateToPlayer,
      modifier = modifier
    )
  }
}

@Composable
fun CompactPickerScreen(
  playlistUiState: PlaylistUiState,
  onNavigateToPlayer: () -> Unit,
  modifier: Modifier = Modifier
) {
  TrackList(
    playlistUiState = playlistUiState,
    modifier = modifier
  )
}

@Composable
fun MediumPickerScreen(
  playlistUiState: PlaylistUiState,
  onNavigateToPlayer: () -> Unit,
  modifier: Modifier = Modifier
) {
  TrackList(
    playlistUiState = playlistUiState,
    modifier = modifier
  )
}

@Composable
fun ExpandedPickerScreen(
  playlistUiState: PlaylistUiState,
  onNavigateToPlayer: () -> Unit,
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
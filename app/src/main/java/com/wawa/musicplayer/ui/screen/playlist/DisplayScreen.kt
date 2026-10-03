package com.wawa.musicplayer.ui.screen.playlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.wawa.musicplayer.R
import com.wawa.musicplayer.data.Track

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
  TrackList(
    tracks = playlistUiState.allTracks,
    modifier = modifier
  )
}

@Composable
fun MediumDisplayScreen(
  playlistUiState: PlaylistUiState,
  onNavigateToEditor: () -> Unit,
  modifier: Modifier = Modifier
) {
  TrackList(
    tracks = playlistUiState.allTracks,
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
    tracks = playlistUiState.allTracks,
    modifier = modifier
  )
}

@Composable
fun TrackList(
  tracks: List<Track>,
  modifier: Modifier = Modifier,
  imageSize: Dp = 80.dp,
  titleLines: Int = 1,
  subtitleLines: Int = 2
) {
  LazyColumn(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    items(tracks) { track ->
      TrackListItem(
        track = track,
        modifier = Modifier.fillMaxWidth(),
        imageSize = imageSize,
        titleLines = titleLines,
        subtitleLines = subtitleLines
      )
    }
  }
}

@Composable
fun TrackListItem(
  track: Track,
  modifier: Modifier = Modifier,
  imageSize: Dp = 80.dp,
  titleLines: Int = 1,
  subtitleLines: Int = 2
) {
  Card(modifier = modifier) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
          .data(track.imageFilePath)
          .crossfade(true)
          .build(),
        contentDescription = null,
        modifier = Modifier.size(imageSize),
        error = painterResource(R.drawable.image_placeholder),
        contentScale = ContentScale.Crop
      )
      Column(
        modifier = Modifier.padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
      ) {
        Text(
          text = track.trackTitle,
          modifier = Modifier.background(Color.Red),
          overflow = TextOverflow.Ellipsis,
          minLines = titleLines,
          maxLines = titleLines,
          style = MaterialTheme.typography.titleMedium
        )
        Text(
          text = track.artistName,
          modifier = Modifier.background(Color.Green),
          overflow = TextOverflow.Ellipsis,
          minLines = subtitleLines,
          maxLines = subtitleLines,
          style = MaterialTheme.typography.bodySmall
        )
      }
    }
  }
}

@Composable
fun TrackDetail(
  track: Track,
  modifier: Modifier = Modifier
) {

}
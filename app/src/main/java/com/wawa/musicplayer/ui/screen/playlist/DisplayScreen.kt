package com.wawa.musicplayer.ui.screen.playlist

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
  onItemClick: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
  when (windowSizeClass.widthSizeClass) {
    WindowWidthSizeClass.Compact -> CompactDisplayScreen(
      playlistUiState = playlistUiState,
      onItemClick = onItemClick,
      modifier = modifier
        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
    )

    WindowWidthSizeClass.Medium -> MediumDisplayScreen(
      playlistUiState = playlistUiState,
      onItemClick = onItemClick,
      modifier = modifier
        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
    )

    WindowWidthSizeClass.Expanded -> ExpandedDisplayScreen(
      playlistUiState = playlistUiState,
      onItemClick = onItemClick,
      modifier = modifier
    )

    else -> CompactDisplayScreen(
      playlistUiState = playlistUiState,
      onItemClick = onItemClick,
      modifier = modifier
    )
  }
}

@Composable
fun CompactDisplayScreen(
  playlistUiState: PlaylistUiState,
  onItemClick: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
  if (playlistUiState.selectedTrackId == null) {
    TrackList(
      selectedTrack = null,
      tracks = playlistUiState.allTracks,
      onItemClick = onItemClick,
      modifier = modifier
    )
  } else {
    TrackDetail(
      track = playlistUiState.allTracks.find { track ->
        track.id == playlistUiState.selectedTrackId
      }!!,
      modifier = modifier
    )
  }
}

@Composable
fun MediumDisplayScreen(
  playlistUiState: PlaylistUiState,
  onItemClick: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
  if (playlistUiState.selectedTrackId == null) {
    TrackList(
      selectedTrack = null,
      tracks = playlistUiState.allTracks,
      onItemClick = onItemClick,
      modifier = modifier
    )
  } else {
    TrackDetail(
      track = playlistUiState.allTracks.find { track ->
        track.id == playlistUiState.selectedTrackId
      }!!,
      modifier = modifier,
      imageSize = 180.dp
    )
  }
}

@Composable
fun ExpandedDisplayScreen(
  playlistUiState: PlaylistUiState,
  onItemClick: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
  Row(modifier = modifier) {
    val selectedTrack = playlistUiState.allTracks.find { track ->
      track.id == playlistUiState.selectedTrackId
    }

    Box(
      modifier = Modifier
        .background(MaterialTheme.colorScheme.surfaceContainerLow)
        .weight(1f)
        .fillMaxHeight()
        .padding(12.dp)
    ) {
      TrackList(
        selectedTrack = selectedTrack,
        tracks = playlistUiState.allTracks,
        onItemClick = onItemClick
      )
    }
    Box(
      modifier = Modifier
        .background(MaterialTheme.colorScheme.surfaceContainerHigh)
        .weight(1f)
        .fillMaxHeight()
        .padding(12.dp)
    ) {
      if (selectedTrack != null) {
        TrackDetail(
          track = selectedTrack,
          modifier = Modifier.fillMaxWidth(),
          imageSize = 180.dp
        )
      }
    }
  }
}

@Composable
fun TrackList(
  selectedTrack: Track?,
  tracks: List<Track>,
  onItemClick: (Int) -> Unit,
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
        onClick = {
          onItemClick(track.id)
        },
        modifier = Modifier.fillMaxWidth(),
        imageSize = imageSize,
        titleLines = titleLines,
        subtitleLines = subtitleLines,
        isSelected = track == selectedTrack
      )
    }
  }
}

@Composable
fun TrackListItem(
  track: Track,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  imageSize: Dp = 80.dp,
  titleLines: Int = 1,
  subtitleLines: Int = 2,
  isSelected: Boolean = false
) {
  Card(
    modifier = modifier.clickable(onClick = onClick),
    colors = CardDefaults.cardColors(
      containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant,
      contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
    ),
  ) {
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
          overflow = TextOverflow.Ellipsis,
          minLines = titleLines,
          maxLines = titleLines,
          style = MaterialTheme.typography.titleMedium
        )
        Text(
          text = track.artistName,
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
  modifier: Modifier = Modifier,
  scrollState: ScrollState = rememberScrollState(),
  imageSize: Dp = 160.dp
) {
  Column(
    modifier = modifier.verticalScroll(scrollState),
    verticalArrangement = Arrangement.spacedBy(12.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
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
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      Text(
        text = track.trackTitle,
        style = MaterialTheme.typography.titleLarge
      )
      Text(
        text = track.artistName,
        style = MaterialTheme.typography.labelLarge
      )
    }

    if (track.lyrics != null) {
      Text(
        text = track.lyrics,
        modifier = Modifier.align(Alignment.Start),
        style = MaterialTheme.typography.bodyLarge
      )
    }
  }
}
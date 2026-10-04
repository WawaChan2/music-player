package com.wawa.musicplayer.ui.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.wawa.musicplayer.data.Track
import com.wawa.musicplayer.ui.screen.playlist.TrackListItem
import com.wawa.musicplayer.ui.theme.MusicPlayerTheme

val sample_track = Track(
  id = 0,
  trackTitle = "The two of us",
  artistName = "Tomoyo Takayanagi and Naomi Ōzora",
  lyrics = null,
  imageFilePath = null,
  audioFilePath = ""
)

@Preview(showBackground = true)
@Composable
fun TrackListItemPreview() {
  MusicPlayerTheme {
    TrackListItem(
      track = sample_track,
      onClick = {},
      isSelected = false
    )
  }
}

@Preview(showBackground = true)
@Composable
fun SelectedTrackListItemPreview() {
  MusicPlayerTheme {
    TrackListItem(
      track = sample_track,
      onClick = {},
      isSelected = true
    )
  }
}

@Preview(showBackground = true)
@Composable
fun TrackListItemDarkModePreview() {
  MusicPlayerTheme(darkTheme = true) {
    TrackListItem(
      track = sample_track,
      onClick = {},
      isSelected = false
    )
  }
}

@Preview(showBackground = true)
@Composable
fun SelectedTrackListItemDarkModePreview() {
  MusicPlayerTheme(darkTheme = true) {
    TrackListItem(
      track = sample_track,
      onClick = {},
      isSelected = true
    )
  }
}
package com.wawa.musicplayer.ui.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.wawa.musicplayer.ui.screen.upload.UploadForm
import com.wawa.musicplayer.ui.screen.upload.UploadState
import com.wawa.musicplayer.ui.theme.MusicPlayerTheme

@Preview(showBackground = true)
@Composable
fun UploadFormPreview() {
  MusicPlayerTheme {
    UploadForm(
      uploadState = UploadState(),
      onTrackTitleTextFieldChange = {},
      onArtistNameTextFieldChange = {},
      onLyricsTextFieldChange = {},
      onEditIconClick = {},
      onSaveButtonClick = {},
      onCancelButtonClick = {}
    )
  }
}
package com.wawa.musicplayer.ui.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.wawa.musicplayer.ui.screen.upload.UploadSubmissionForm
import com.wawa.musicplayer.ui.theme.MusicPlayerTheme

@Preview(showBackground = true)
@Composable
fun UploadSubmissionFormPreview() {
  MusicPlayerTheme {
    UploadSubmissionForm()
  }
}
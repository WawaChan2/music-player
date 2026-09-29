package com.wawa.musicplayer.ui.screen.upload

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wawa.musicplayer.R
import com.wawa.musicplayer.ui.extension.dashedBorder

@Composable
fun UploadScreen(
  windowSizeClass: WindowSizeClass,
  modifier: Modifier = Modifier
) {
  when (windowSizeClass.widthSizeClass) {
    WindowWidthSizeClass.Compact -> CompactUploadScreen(
      uploadAudioButtonOnClick = {},
      modifier = modifier
    )

    WindowWidthSizeClass.Medium -> MediumUploadScreen(
      uploadAudioButtonOnClick = {},
      modifier = modifier
    )

    WindowWidthSizeClass.Expanded -> ExpandedUploadScreen(
      uploadAudioButtonOnClick = {},
      modifier = modifier
    )

    else -> CompactUploadScreen(
      uploadAudioButtonOnClick = {},
      modifier = modifier
    )
  }
}

@Composable
fun CompactUploadScreen(
  uploadAudioButtonOnClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    UploadAudioBox(
      uploadAudioButtonOnClick = uploadAudioButtonOnClick
    )
  }
}

@Composable
fun MediumUploadScreen(
  uploadAudioButtonOnClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
  ) {
    UploadAudioBox(
      uploadAudioButtonOnClick = uploadAudioButtonOnClick,
      widthPercentage = .7f,
      uploadIconSize = 84.dp,
      padding = 16.dp
    )
  }
}

@Composable
fun ExpandedUploadScreen(
  uploadAudioButtonOnClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
  ) {
    UploadAudioBox(
      uploadAudioButtonOnClick = uploadAudioButtonOnClick,
      widthPercentage = .6f,
      uploadIconSize = 96.dp,
      padding = 20.dp
    )
  }
}

@Composable
fun UploadAudioBox(
  uploadAudioButtonOnClick: () -> Unit,
  modifier: Modifier = Modifier,
  widthPercentage: Float = .8f,
  uploadIconSize: Dp = 72.dp,
  padding: Dp = 12.dp,
  cornerRadius: Dp = 16.dp,
  strokeWidth: Dp = 4.dp,
  dashLength: Dp = 12.dp,
  gapLength: Dp = 12.dp,
) {
  Row {
    Spacer(modifier = Modifier.weight((1f - widthPercentage) / 2f))
    Column(
      modifier = modifier
        .dashedBorder(
          color = MaterialTheme.colorScheme.primary,
          shape = RoundedCornerShape(cornerRadius),
          strokeWidth = strokeWidth,
          dashLength = dashLength,
          gapLength = gapLength
        )
        .weight(widthPercentage)
        .padding(padding),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Icon(
        imageVector = ImageVector.vectorResource(R.drawable.upload_24px),
        contentDescription = stringResource(R.string.upload_nav),
        modifier = Modifier.size(uploadIconSize),
        tint = MaterialTheme.colorScheme.primary
      )
      Button(
        onClick = uploadAudioButtonOnClick,
        elevation = ButtonDefaults.buttonElevation(
          defaultElevation = 8.dp
        )
      ) {
        Text(text = stringResource(R.string.upload_audio_button_text))
      }
    }
    Spacer(modifier = Modifier.weight((1f - widthPercentage) / 2f))
  }
}

@Composable
fun UploadSubmissionForm(modifier: Modifier = Modifier) {
  val trackTitleTextFieldState = rememberTextFieldState(initialText = "")
  val artistNameTextFieldState = rememberTextFieldState(initialText = "")
  val lyricsTextFieldState = rememberTextFieldState(initialText = "")

  Column(modifier = modifier) {
    TextField(
      state = trackTitleTextFieldState,
      label = { Text(text = "Track title *") },
      placeholder = { Text(text = "Hello") },
    )
    TextField(
      state = artistNameTextFieldState
    )
    TextField(
      state = lyricsTextFieldState
    )
  }
}
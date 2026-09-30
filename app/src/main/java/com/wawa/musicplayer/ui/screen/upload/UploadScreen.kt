package com.wawa.musicplayer.ui.screen.upload

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
  uploadState: UploadState,
  onTrackTitleTextFieldChange: (String) -> Unit,
  onArtistNameTextFieldChange: (String) -> Unit,
  onLyricsTextFieldChange: (String) -> Unit,
  uploadAudioButtonOnClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  when (windowSizeClass.widthSizeClass) {
    WindowWidthSizeClass.Compact -> CompactUploadScreen(
      uploadState = uploadState,
      uploadAudioButtonOnClick = uploadAudioButtonOnClick,
      onTrackTitleTextFieldChange = onTrackTitleTextFieldChange,
      onArtistNameTextFieldChange = onArtistNameTextFieldChange,
      onLyricsTextFieldChange = onLyricsTextFieldChange,
      modifier = modifier
    )

    WindowWidthSizeClass.Medium -> MediumUploadScreen(
      uploadState = uploadState,
      uploadAudioButtonOnClick = uploadAudioButtonOnClick,
      onTrackTitleTextFieldChange = onTrackTitleTextFieldChange,
      onArtistNameTextFieldChange = onArtistNameTextFieldChange,
      onLyricsTextFieldChange = onLyricsTextFieldChange,
      modifier = modifier
    )

    WindowWidthSizeClass.Expanded -> ExpandedUploadScreen(
      uploadState = uploadState,
      uploadAudioButtonOnClick = uploadAudioButtonOnClick,
      onTrackTitleTextFieldChange = onTrackTitleTextFieldChange,
      onArtistNameTextFieldChange = onArtistNameTextFieldChange,
      onLyricsTextFieldChange = onLyricsTextFieldChange,
      modifier = modifier
    )

    else -> CompactUploadScreen(
      uploadState = uploadState,
      uploadAudioButtonOnClick = uploadAudioButtonOnClick,
      onTrackTitleTextFieldChange = onTrackTitleTextFieldChange,
      onArtistNameTextFieldChange = onArtistNameTextFieldChange,
      onLyricsTextFieldChange = onLyricsTextFieldChange,
      modifier = modifier
    )
  }
}

@Composable
fun CompactUploadScreen(
  uploadState: UploadState,
  onTrackTitleTextFieldChange: (String) -> Unit,
  onArtistNameTextFieldChange: (String) -> Unit,
  onLyricsTextFieldChange: (String) -> Unit,
  uploadAudioButtonOnClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(32.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    UploadAudioBox(
      uploadAudioButtonOnClick = uploadAudioButtonOnClick
    )
    BottomContent(
      uploadProcessingState = uploadState.uploadProcessingState,
      loading = {
        LoadingSpinner()
      },
      success = {
        UploadForm(
          uploadState = uploadState,
          onTrackTitleTextFieldChange = onTrackTitleTextFieldChange,
          onArtistNameTextFieldChange = onArtistNameTextFieldChange,
          onLyricsTextFieldChange = onLyricsTextFieldChange
        )
      },
      incorrectMimeType = {
        IncorrectMimeTypeWarning()
      },
      error = {
        ErrorWarning()
      }
    ) { }
  }
}

@Composable
fun MediumUploadScreen(
  uploadState: UploadState,
  onTrackTitleTextFieldChange: (String) -> Unit,
  onArtistNameTextFieldChange: (String) -> Unit,
  onLyricsTextFieldChange: (String) -> Unit,
  uploadAudioButtonOnClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(32.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    UploadAudioBox(
      uploadAudioButtonOnClick = uploadAudioButtonOnClick,
      widthFraction = .5f,
      uploadIconSize = 84.dp,
      padding = 16.dp
    )
    BottomContent(
      uploadProcessingState = uploadState.uploadProcessingState,
      loading = {
        LoadingSpinner()
      },
      success = {
        UploadForm(
          uploadState = uploadState,
          onTrackTitleTextFieldChange = onTrackTitleTextFieldChange,
          onArtistNameTextFieldChange = onArtistNameTextFieldChange,
          onLyricsTextFieldChange = onLyricsTextFieldChange
        )
      },
      incorrectMimeType = {
        IncorrectMimeTypeWarning()
      },
      error = {
        ErrorWarning()
      }
    ) { }
  }
}

@Composable
fun ExpandedUploadScreen(
  uploadState: UploadState,
  onTrackTitleTextFieldChange: (String) -> Unit,
  onArtistNameTextFieldChange: (String) -> Unit,
  onLyricsTextFieldChange: (String) -> Unit,
  uploadAudioButtonOnClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(32.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    UploadAudioBox(
      uploadAudioButtonOnClick = uploadAudioButtonOnClick,
      widthFraction = .4f,
      uploadIconSize = 84.dp,
      padding = 20.dp
    )
    BottomContent(
      uploadProcessingState = uploadState.uploadProcessingState,
      loading = {
        LoadingSpinner()
      },
      success = {
        UploadForm(
          uploadState = uploadState,
          onTrackTitleTextFieldChange = onTrackTitleTextFieldChange,
          onArtistNameTextFieldChange = onArtistNameTextFieldChange,
          onLyricsTextFieldChange = onLyricsTextFieldChange,
          isVariant = true,
          textFieldWidthFraction = .95f
        )
      },
      incorrectMimeType = {
        IncorrectMimeTypeWarning()
      },
      error = {
        ErrorWarning()
      }
    ) { }
  }
}

@Composable
fun UploadAudioBox(
  uploadAudioButtonOnClick: () -> Unit,
  modifier: Modifier = Modifier,
  widthFraction: Float = .8f,
  uploadIconSize: Dp = 72.dp,
  padding: Dp = 12.dp,
  cornerRadius: Dp = 16.dp,
  strokeWidth: Dp = 4.dp,
  dashLength: Dp = 12.dp,
  gapLength: Dp = 12.dp,
) {
  Column(
    modifier = modifier
      .dashedBorder(
        color = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(cornerRadius),
        strokeWidth = strokeWidth,
        dashLength = dashLength,
        gapLength = gapLength
      )
      .fillMaxWidth(widthFraction)
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
}

@Composable
fun BottomContent(
  uploadProcessingState: UploadProcessingState?,
  loading: @Composable () -> Unit,
  success: @Composable () -> Unit,
  incorrectMimeType: @Composable () -> Unit,
  error: @Composable () -> Unit,
  initial: @Composable () -> Unit
) {
  when (uploadProcessingState) {
    UploadLoading -> loading()
    UploadSuccess -> success()
    UploadIncorrectMimeType -> incorrectMimeType()
    UploadError -> error()
    else -> initial()
  }
}

@Composable
fun LoadingSpinner(modifier: Modifier = Modifier) {

}

@Composable
fun UploadForm(
  uploadState: UploadState,
  onTrackTitleTextFieldChange: (String) -> Unit,
  onArtistNameTextFieldChange: (String) -> Unit,
  onLyricsTextFieldChange: (String) -> Unit,
  modifier: Modifier = Modifier,
  isVariant: Boolean = false,
  verticalSpacing: Dp = 4.dp,
  textFieldWidthFraction: Float = .9f
) {
  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(verticalSpacing)
  ) {
    val isTrackTitleTextFieldStateValid = uploadState.trackTitle.isNotBlank()
    val isArtistNameTextFieldStateValid = uploadState.artistName.isNotBlank()

    if (isVariant) {
      Row(
        modifier = Modifier.fillMaxWidth(textFieldWidthFraction),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        TextField(
          value = uploadState.trackTitle,
          onValueChange = onTrackTitleTextFieldChange,
          modifier = Modifier.weight(1f),
          label = { Text(text = stringResource(R.string.track_title_text_field)) },
          supportingText = {
            if (!isTrackTitleTextFieldStateValid) {
              Text(text = stringResource(R.string.empty_field_error_message))
            }
          },
          isError = !isTrackTitleTextFieldStateValid,
          singleLine = true
        )
        TextField(
          value = uploadState.artistName,
          onValueChange = onArtistNameTextFieldChange,
          modifier = Modifier.weight(1f),
          label = { Text(text = stringResource(R.string.artist_name_text_field)) },
          supportingText = {
            if (!isArtistNameTextFieldStateValid) {
              Text(text = stringResource(R.string.empty_field_error_message))
            }
          },
          isError = !isArtistNameTextFieldStateValid,
          singleLine = true
        )
      }
    } else {
      TextField(
        value = uploadState.trackTitle,
        onValueChange = onTrackTitleTextFieldChange,
        modifier = Modifier.fillMaxWidth(textFieldWidthFraction),
        label = { Text(text = stringResource(R.string.track_title_text_field)) },
        supportingText = {
          if (!isTrackTitleTextFieldStateValid) {
            Text(text = stringResource(R.string.empty_field_error_message))
          }
        },
        isError = !isTrackTitleTextFieldStateValid,
        singleLine = true
      )
      TextField(
        value = uploadState.artistName,
        onValueChange = onArtistNameTextFieldChange,
        modifier = Modifier.fillMaxWidth(textFieldWidthFraction),
        label = { Text(text = stringResource(R.string.artist_name_text_field)) },
        supportingText = {
          if (!isArtistNameTextFieldStateValid) {
            Text(text = stringResource(R.string.empty_field_error_message))
          }
        },
        isError = !isArtistNameTextFieldStateValid,
        singleLine = true
      )
    }

    OutlinedTextField(
      value = uploadState.lyrics,
      onValueChange = onLyricsTextFieldChange,
      modifier = Modifier.fillMaxWidth(textFieldWidthFraction),
      label = { Text(text = stringResource(R.string.lyrics_text_field)) }
    )
  }
}

@Composable
fun ErrorWarning(modifier: Modifier = Modifier) {

}

@Composable
fun IncorrectMimeTypeWarning(modifier: Modifier = Modifier) {

}
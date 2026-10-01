package com.wawa.musicplayer.ui.screen.upload

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.wawa.musicplayer.R
import com.wawa.musicplayer.ui.extension.dashedBorder

@Composable
fun UploadScreen(
  windowSizeClass: WindowSizeClass,
  uploadState: UploadState,
  onTrackTitleTextFieldChange: (String) -> Unit,
  onArtistNameTextFieldChange: (String) -> Unit,
  onLyricsTextFieldChange: (String) -> Unit,
  onEditIconClick: () -> Unit,
  onUploadAudioButtonClick: () -> Unit,
  onSaveButtonClick: () -> Unit,
  onCancelButtonClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  val scrollState = rememberScrollState()

  when (windowSizeClass.widthSizeClass) {
    WindowWidthSizeClass.Compact -> CompactUploadScreen(
      uploadState = uploadState,
      onTrackTitleTextFieldChange = onTrackTitleTextFieldChange,
      onArtistNameTextFieldChange = onArtistNameTextFieldChange,
      onLyricsTextFieldChange = onLyricsTextFieldChange,
      onEditIconClick = onEditIconClick,
      onUploadAudioButtonClick = onUploadAudioButtonClick,
      onSaveButtonClick = onSaveButtonClick,
      onCancelButtonClick = onCancelButtonClick,
      modifier = modifier,
      scrollState = scrollState
    )

    WindowWidthSizeClass.Medium -> MediumUploadScreen(
      uploadState = uploadState,
      onTrackTitleTextFieldChange = onTrackTitleTextFieldChange,
      onArtistNameTextFieldChange = onArtistNameTextFieldChange,
      onLyricsTextFieldChange = onLyricsTextFieldChange,
      onEditIconClick = onEditIconClick,
      onUploadAudioButtonClick = onUploadAudioButtonClick,
      onSaveButtonClick = onSaveButtonClick,
      onCancelButtonClick = onCancelButtonClick,
      modifier = modifier,
      scrollState = scrollState
    )

    WindowWidthSizeClass.Expanded -> ExpandedUploadScreen(
      uploadState = uploadState,
      onTrackTitleTextFieldChange = onTrackTitleTextFieldChange,
      onArtistNameTextFieldChange = onArtistNameTextFieldChange,
      onLyricsTextFieldChange = onLyricsTextFieldChange,
      onEditIconClick = onEditIconClick,
      onUploadAudioButtonClick = onUploadAudioButtonClick,
      onSaveButtonClick = onSaveButtonClick,
      onCancelButtonClick = onCancelButtonClick,
      modifier = modifier,
      scrollState = scrollState
    )

    else -> CompactUploadScreen(
      uploadState = uploadState,
      onTrackTitleTextFieldChange = onTrackTitleTextFieldChange,
      onArtistNameTextFieldChange = onArtistNameTextFieldChange,
      onLyricsTextFieldChange = onLyricsTextFieldChange,
      onEditIconClick = onEditIconClick,
      onUploadAudioButtonClick = onUploadAudioButtonClick,
      onSaveButtonClick = onSaveButtonClick,
      onCancelButtonClick = onCancelButtonClick,
      modifier = modifier,
      scrollState = scrollState
    )
  }
}

@Composable
fun CompactUploadScreen(
  uploadState: UploadState,
  onTrackTitleTextFieldChange: (String) -> Unit,
  onArtistNameTextFieldChange: (String) -> Unit,
  onLyricsTextFieldChange: (String) -> Unit,
  onEditIconClick: () -> Unit,
  onUploadAudioButtonClick: () -> Unit,
  onSaveButtonClick: () -> Unit,
  onCancelButtonClick: () -> Unit,
  modifier: Modifier = Modifier,
  scrollState: ScrollState = rememberScrollState()
) {
  Column(
    modifier = modifier
      .verticalScroll(scrollState)
      .height(IntrinsicSize.Max)
      .padding(8.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    UploadAudioBox(
      onUploadAudioButtonClick = onUploadAudioButtonClick
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
          onEditIconClick = onEditIconClick,
          onSaveButtonClick = onSaveButtonClick,
          onCancelButtonClick = onCancelButtonClick,
        )
      },
      incorrectMimeType = {
        IncorrectMimeTypeWarning()
      },
      error = {
        ErrorWarning()
      }
    ) {

    }
  }
}

@Composable
fun MediumUploadScreen(
  uploadState: UploadState,
  onTrackTitleTextFieldChange: (String) -> Unit,
  onArtistNameTextFieldChange: (String) -> Unit,
  onLyricsTextFieldChange: (String) -> Unit,
  onEditIconClick: () -> Unit,
  onUploadAudioButtonClick: () -> Unit,
  onSaveButtonClick: () -> Unit,
  onCancelButtonClick: () -> Unit,
  modifier: Modifier = Modifier,
  scrollState: ScrollState = rememberScrollState()
) {
  Column(
    modifier = modifier
      .verticalScroll(scrollState)
      .padding(8.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    UploadAudioBox(
      onUploadAudioButtonClick = onUploadAudioButtonClick,
      widthFraction = .5f,
      uploadIconSize = 84.dp,
      padding = 16.dp
    )
    BottomContent(
      uploadProcessingState = uploadState.uploadProcessingState,
      loading = {
        LoadingSpinner(
          size = 160.dp,
          strokeWidth = 8.5.dp
        )
      },
      success = {
        UploadForm(
          uploadState = uploadState,
          onTrackTitleTextFieldChange = onTrackTitleTextFieldChange,
          onArtistNameTextFieldChange = onArtistNameTextFieldChange,
          onLyricsTextFieldChange = onLyricsTextFieldChange,
          onEditIconClick = onEditIconClick,
          onSaveButtonClick = onSaveButtonClick,
          onCancelButtonClick = onCancelButtonClick,
          coverArtSize = 192.dp,
          borderWidth = 8.dp,
          editIconSize = 36.dp,
          iconOuterPadding = 4.dp
        )
      },
      incorrectMimeType = {
        IncorrectMimeTypeWarning(iconSize = 84.dp)
      },
      error = {
        ErrorWarning(iconSize = 84.dp)
      }
    ) {

    }
  }
}

@Composable
fun ExpandedUploadScreen(
  uploadState: UploadState,
  onTrackTitleTextFieldChange: (String) -> Unit,
  onArtistNameTextFieldChange: (String) -> Unit,
  onLyricsTextFieldChange: (String) -> Unit,
  onEditIconClick: () -> Unit,
  onUploadAudioButtonClick: () -> Unit,
  onSaveButtonClick: () -> Unit,
  onCancelButtonClick: () -> Unit,
  modifier: Modifier = Modifier,
  scrollState: ScrollState = rememberScrollState()
) {
  Column(
    modifier = modifier
      .verticalScroll(scrollState)
      .padding(8.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    UploadAudioBox(
      onUploadAudioButtonClick = onUploadAudioButtonClick,
      widthFraction = .4f,
      uploadIconSize = 84.dp,
      padding = 20.dp
    )
    BottomContent(
      uploadProcessingState = uploadState.uploadProcessingState,
      loading = {
        LoadingSpinner(
          size = 192.dp,
          strokeWidth = 9.dp
        )
      },
      success = {
        UploadForm(
          uploadState = uploadState,
          onTrackTitleTextFieldChange = onTrackTitleTextFieldChange,
          onArtistNameTextFieldChange = onArtistNameTextFieldChange,
          onLyricsTextFieldChange = onLyricsTextFieldChange,
          onEditIconClick = onEditIconClick,
          onSaveButtonClick = onSaveButtonClick,
          onCancelButtonClick = onCancelButtonClick,
          isVariant = true,
          widthFraction = .95f,
          coverArtSize = 256.dp,
          borderWidth = 10.dp,
          editIconSize = 44.dp,
          iconOuterPadding = 8.dp,
          lyricsLineHeight = 5
        )
      },
      incorrectMimeType = {
        IncorrectMimeTypeWarning(iconSize = 84.dp)
      },
      error = {
        ErrorWarning(iconSize = 84.dp)
      }
    ) {

    }
  }
}

@Composable
fun UploadAudioBox(
  onUploadAudioButtonClick: () -> Unit,
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
      onClick = onUploadAudioButtonClick,
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
fun LoadingSpinner(
  modifier: Modifier = Modifier,
  size: Dp = 128.dp,
  strokeWidth: Dp = 8.dp
) {
  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    CircularProgressIndicator(
      modifier = modifier.size(size),
      strokeWidth = strokeWidth
    )
    Text(
      text = "Loading...",
      color = MaterialTheme.colorScheme.primary
    )
  }
}

@Composable
fun UploadForm(
  uploadState: UploadState,
  onTrackTitleTextFieldChange: (String) -> Unit,
  onArtistNameTextFieldChange: (String) -> Unit,
  onLyricsTextFieldChange: (String) -> Unit,
  onEditIconClick: () -> Unit,
  onSaveButtonClick: () -> Unit,
  onCancelButtonClick: () -> Unit,
  modifier: Modifier = Modifier,
  isVariant: Boolean = false,
  coverArtSize: Dp = 160.dp,
  borderWidth: Dp = 6.dp,
  editIconSize: Dp = 28.dp,
  iconInnerPadding: Dp = 6.dp,
  iconOuterPadding: Dp = 2.dp,
  verticalSpacing: Dp = 4.dp,
  widthFraction: Float = .9f,
  lyricsLineHeight: Int = 3
) {
  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(verticalSpacing),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    val isTrackTitleTextFieldStateValid = uploadState.trackTitle.isNotBlank()
    val isArtistNameTextFieldStateValid = uploadState.artistName.isNotBlank()

    Box(modifier = Modifier.padding(16.dp)) {
      AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
          .data(uploadState.bitmap)
          .crossfade(true)
          .build(),
        contentDescription = null,
        modifier = Modifier
          .size(coverArtSize)
          .border(
            width = borderWidth,
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = CircleShape
          )
          .clip(CircleShape),
        error = painterResource(R.drawable.image_placeholder),
        contentScale = ContentScale.Crop
      )
      Icon(
        imageVector = ImageVector.vectorResource(R.drawable.edit_24px),
        contentDescription = null,
        modifier = Modifier
          .padding(iconOuterPadding)
          .clip(CircleShape)
          .background(MaterialTheme.colorScheme.secondaryContainer)
          .padding(iconInnerPadding)
          .size(editIconSize)
          .align(Alignment.BottomEnd)
          .clickable(onClick = onEditIconClick),
        tint = MaterialTheme.colorScheme.onSecondaryContainer
      )
    }

    if (isVariant) {
      Row(
        modifier = Modifier.fillMaxWidth(widthFraction),
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
        modifier = Modifier.fillMaxWidth(widthFraction),
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
        modifier = Modifier.fillMaxWidth(widthFraction),
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
      modifier = Modifier.fillMaxWidth(widthFraction),
      label = { Text(text = stringResource(R.string.lyrics_text_field)) },
      minLines = lyricsLineHeight,
      maxLines = lyricsLineHeight
    )

    if (isVariant) {
      Row(
        modifier = modifier
          .padding(top = 24.dp)
          .fillMaxWidth(widthFraction),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        Button(
          onClick = onSaveButtonClick,
          modifier = Modifier.weight(1f)
        ) {
          Text(text = stringResource(R.string.save_button_text))
        }
        OutlinedButton(
          onClick = onCancelButtonClick,
          modifier = Modifier.weight(1f)
        ) {
          Text(text = stringResource(R.string.cancel_button_text))
        }
      }
    } else {
      Column(
        modifier = Modifier.padding(top = 24.dp),
        verticalArrangement = Arrangement.spacedBy(verticalSpacing)
      ) {
        Button(
          onClick = onSaveButtonClick,
          modifier = Modifier.fillMaxWidth(widthFraction)
        ) {
          Text(text = stringResource(R.string.save_button_text))
        }
        OutlinedButton(
          onClick = onCancelButtonClick,
          modifier = Modifier.fillMaxWidth(widthFraction)
        ) {
          Text(text = stringResource(R.string.cancel_button_text))
        }
      }
    }
  }
}

@Composable
fun ErrorWarning(
  modifier: Modifier = Modifier,
  iconSize: Dp = 64.dp
) {
  WarningLayout(
    title = stringResource(R.string.error_warning_title),
    subtitle = stringResource(R.string.error_warning_subtitle),
    icon = ImageVector.vectorResource(R.drawable.error_24px),
    modifier = modifier,
    iconSize = iconSize
  )
}

@Composable
fun IncorrectMimeTypeWarning(
  modifier: Modifier = Modifier,
  iconSize: Dp = 64.dp
) {
  WarningLayout(
    title = stringResource(R.string.incorrect_mime_type_warning_title),
    subtitle = stringResource(R.string.incorrect_mime_type_warning_subtitle),
    icon = ImageVector.vectorResource(R.drawable.warning_24px),
    modifier = modifier,
    iconSize = iconSize
  )
}

@Composable
fun WarningLayout(
  title: String,
  subtitle: String,
  icon: ImageVector,
  modifier: Modifier = Modifier,
  iconSize: Dp = 64.dp
) {
  Column(
    modifier = modifier.fillMaxSize(),
    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = title,
      color = MaterialTheme.colorScheme.error,
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.headlineLarge
    )
    Icon(
      imageVector = icon,
      contentDescription = null,
      modifier = Modifier.size(iconSize),
      tint = MaterialTheme.colorScheme.error
    )
    Text(
      text = subtitle,
      color = MaterialTheme.colorScheme.error,
      textAlign = TextAlign.Center
    )
  }
}
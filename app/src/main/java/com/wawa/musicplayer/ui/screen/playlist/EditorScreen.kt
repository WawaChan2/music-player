package com.wawa.musicplayer.ui.screen.playlist

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun EditorScreen(
  windowSizeClass: WindowSizeClass,
  playlistUiState: PlaylistUiState,
  onNavigateBack: () -> Unit,
  onOptionClick: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
  BackHandler { onNavigateBack() }

  when (windowSizeClass.widthSizeClass) {
    WindowWidthSizeClass.Compact -> CompactEditorScreen(
      playlistUiState = playlistUiState,
      onOptionClick = onOptionClick,
      modifier = modifier
        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
    )

    WindowWidthSizeClass.Medium -> MediumEditorScreen(
      playlistUiState = playlistUiState,
      onOptionClick = onOptionClick,
      modifier = modifier
        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
    )

    WindowWidthSizeClass.Expanded -> ExpandedEditorScreen(
      playlistUiState = playlistUiState,
      onOptionClick = onOptionClick,
      modifier = modifier
    )

    else -> CompactEditorScreen(
      playlistUiState = playlistUiState,
      onOptionClick = onOptionClick,
      modifier = modifier
        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
    )
  }
}

@Composable
fun CompactEditorScreen(
  playlistUiState: PlaylistUiState,
  onOptionClick: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
  Box(modifier = modifier) {
    SingleChoiceSegmentedButtonColumn(
      selectedOptionIndex = playlistUiState.selectedOptionIndex,
      options = playlistUiState.options,
      modifier = Modifier.align(Alignment.CenterEnd),
      onOptionClick = onOptionClick
    )
  }
}

@Composable
fun MediumEditorScreen(
  playlistUiState: PlaylistUiState,
  onOptionClick: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
  Box(modifier = modifier) {
    SingleChoiceSegmentedButtonColumn(
      selectedOptionIndex = playlistUiState.selectedOptionIndex,
      options = playlistUiState.options,
      modifier = Modifier.align(Alignment.CenterEnd),
      onOptionClick = onOptionClick
    )
  }
}

@Composable
fun ExpandedEditorScreen(
  playlistUiState: PlaylistUiState,
  onOptionClick: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
  Row(modifier = modifier) {
    Box(
      modifier = Modifier
        .background(MaterialTheme.colorScheme.surfaceContainerLow)
        .weight(1f)
        .fillMaxHeight()
        .padding(12.dp)
    ) {

    }
    Box(
      modifier = Modifier
        .background(MaterialTheme.colorScheme.surfaceContainerHigh)
        .weight(1f)
        .fillMaxHeight()
        .padding(12.dp)
    ) {
      SingleChoiceSegmentedButtonColumn(
        selectedOptionIndex = playlistUiState.selectedOptionIndex,
        options = playlistUiState.options,
        modifier = Modifier.align(Alignment.CenterEnd),
        backgroundColor = MaterialTheme.colorScheme.surfaceContainer,
        onOptionClick = onOptionClick,
        buttonSize = 56.dp,
        iconSize = 28.dp
      )
    }
  }
}

@Composable
fun SingleChoiceSegmentedButtonColumn(
  selectedOptionIndex: Int,
  @DrawableRes options: List<Int>,
  onOptionClick: (Int) -> Unit,
  modifier: Modifier = Modifier,
  backgroundColor: Color = MaterialTheme.colorScheme.surfaceContainerHighest,
  buttonSize: Dp = 48.dp,
  iconSize: Dp = 24.dp
) {
  Column(
    modifier = modifier
      .clip(RoundedCornerShape(50))
      .background(backgroundColor)
      .padding(8.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    options.forEachIndexed { index, iconId ->
      RoundButton(
        isSelected = selectedOptionIndex == index,
        iconId = iconId,
        onClick = { onOptionClick(index) },
        buttonSize = buttonSize,
        iconSize = iconSize
      )
    }
  }
}

@Composable
fun RoundButton(
  isSelected: Boolean,
  @DrawableRes iconId: Int,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  buttonSize: Dp = 48.dp,
  iconSize: Dp = 24.dp
) {
  Button(
    onClick = onClick,
    modifier = modifier.size(buttonSize),
    shape = CircleShape,
    colors = ButtonDefaults.buttonColors(
      containerColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
      contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
    ),
    contentPadding = PaddingValues(0.dp)
  ) {
    Icon(
      imageVector = ImageVector.vectorResource(iconId),
      contentDescription = null,
      modifier = Modifier.size(iconSize)
    )
  }
}
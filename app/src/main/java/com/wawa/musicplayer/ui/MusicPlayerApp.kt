package com.wawa.musicplayer.ui

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wawa.musicplayer.MIME_TYPE_AUDIO
import com.wawa.musicplayer.MIME_TYPE_IMAGE
import com.wawa.musicplayer.R
import com.wawa.musicplayer.data.getAudioMetadata
import com.wawa.musicplayer.data.getBitmap
import com.wawa.musicplayer.ui.screen.navigation.AppNavigation
import com.wawa.musicplayer.ui.screen.navigation.Display
import com.wawa.musicplayer.ui.screen.navigation.Editor
import com.wawa.musicplayer.ui.screen.navigation.NavigationViewModel
import com.wawa.musicplayer.ui.screen.navigation.NowPlaying
import com.wawa.musicplayer.ui.screen.navigation.PlaylistTab
import com.wawa.musicplayer.ui.screen.navigation.TopLevelDestination
import com.wawa.musicplayer.ui.screen.playlist.PlaylistViewModel
import com.wawa.musicplayer.ui.screen.upload.UploadError
import com.wawa.musicplayer.ui.screen.upload.UploadIncorrectMimeType
import com.wawa.musicplayer.ui.screen.upload.UploadLoading
import com.wawa.musicplayer.ui.screen.upload.UploadViewModel

@Composable
fun MusicPlayerApp(
  windowSizeClass: WindowSizeClass,
  navigationViewModel: NavigationViewModel = hiltViewModel(),
  uploadViewModel: UploadViewModel = hiltViewModel(),
  playlistViewModel: PlaylistViewModel = hiltViewModel()
) {
  val navigationState by navigationViewModel.navigationState.collectAsStateWithLifecycle()
  val uploadState by uploadViewModel.uploadState.collectAsStateWithLifecycle()
  val playlistUiState by playlistViewModel.playlistUiState.collectAsStateWithLifecycle()

  NavigationSuiteScaffold(
    navigationSuiteItems = {
      TopLevelDestination.entries.forEach { topLevelDestination ->
        item(
          selected = topLevelDestination.tab == navigationState.selectedTab,
          onClick = { navigationViewModel.selectTab(topLevelDestination.tab) },
          icon = {
            Icon(
              imageVector = ImageVector.vectorResource(topLevelDestination.iconId),
              contentDescription = stringResource(topLevelDestination.labelId)
            )
          },
          label = {
            Text(text = stringResource(topLevelDestination.labelId))
          }
        )
      }
    },
    layoutType = when (windowSizeClass.widthSizeClass) {
      WindowWidthSizeClass.Compact -> NavigationSuiteType.NavigationBar
      WindowWidthSizeClass.Medium -> NavigationSuiteType.NavigationRail
      WindowWidthSizeClass.Expanded -> NavigationSuiteType.NavigationDrawer
      else -> NavigationSuiteType.NavigationBar
    }
  ) {
    Scaffold(
      modifier = Modifier.fillMaxSize(),
      topBar = {
        val screen = navigationState.backStackByTab[PlaylistTab]!!.last()

        MusicPlayerAppTopBar(
          windowSizeClass = windowSizeClass,
          hasNavigationIcon = navigationState.selectedTab == PlaylistTab && (screen == Editor || screen == NowPlaying),
          onNavigateBack = { navigationViewModel.navigateBackOnTab(PlaylistTab) }
        )
      },
      floatingActionButton = {
        if (navigationState.selectedTab == PlaylistTab) {
          val screen = navigationState.backStackByTab[PlaylistTab]!!.last()

          if (screen == Display || screen == Editor) {
            val to = when (screen) {
              Display -> Editor
              Editor -> NowPlaying
            }

            MusicPlayerAppFloatingActionButton(
              windowSizeClass = windowSizeClass,
              onClick = {
                navigationViewModel.navigateToScreenOnTab(PlaylistTab, to)
              }
            )
          }
        }
      }
    ) { innerPadding ->
      val context = LocalContext.current

      val audioPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
      ) { uri ->
        uri?.let { uri ->
          uploadViewModel.setUploadProcessingState(UploadLoading)

          val mimeType = context.contentResolver.getType(uri)

          if (mimeType?.startsWith("audio/") == true) {
            val audioMetadata = getAudioMetadata(context, uri)

            audioMetadata?.let { audioMetadata ->
              uploadViewModel.updateUploadForm(audioMetadata, uri)
            } ?: {
              uploadViewModel.setUploadProcessingState(UploadError)
            }
          } else {
            uploadViewModel.setUploadProcessingState(UploadIncorrectMimeType)
          }
        }
      }

      val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
      ) { uri ->
        uri?.let { uri ->
          val mimeType = context.contentResolver.getType(uri)

          if (mimeType?.startsWith("image/") == true) {
            val bitmap = getBitmap(context, uri)

            uploadViewModel.setBitmap(bitmap)
          } else {
            uploadViewModel.setBitmap(null)
          }
        }
      }

      AppNavigation(
        windowSizeClass = windowSizeClass,
        navigationState = navigationState,
        uploadState = uploadState,
        playlistUiState = playlistUiState,
        onNavigateBack = {
          navigationViewModel.navigateBackOnTab(
            tab = navigationState.selectedTab
          )
        },
        onTrackTitleTextFieldChange = uploadViewModel::onTrackTitleChange,
        onArtistNameTextFieldChange = uploadViewModel::onArtistNameChange,
        onLyricsTextFieldChange = uploadViewModel::onLyricsChange,
        onEditIconClick = {
          imagePickerLauncher.launch(input = MIME_TYPE_IMAGE)
        },
        onUploadAudioButtonClick = {
          audioPickerLauncher.launch(input = MIME_TYPE_AUDIO)
        },
        onSaveButtonClick = uploadViewModel::saveTrack,
        onCancelButtonClick = { uploadViewModel.setUploadProcessingState(null) },
        onDialogClose = {
          uploadViewModel.setUploadProcessingState(null)
          uploadViewModel.setSaveState(null)
        },
        onItemClick = playlistViewModel::selectTrackById,
        modifier = Modifier.padding(innerPadding)
      )
    }
  }
}

@Composable
fun MusicPlayerAppTopBar(
  windowSizeClass: WindowSizeClass,
  modifier: Modifier = Modifier,
  hasNavigationIcon: Boolean = false,
  onNavigateBack: () -> Unit = {}
) {
  when (windowSizeClass.widthSizeClass) {
    WindowWidthSizeClass.Compact -> BaseAppTopBar(
      modifier = modifier,
      hasNavigationIcon = hasNavigationIcon,
      onNavigateBack = onNavigateBack
    )

    WindowWidthSizeClass.Medium -> BaseAppTopBar(
      modifier = modifier,
      hasNavigationIcon = hasNavigationIcon,
      onNavigateBack = onNavigateBack
    )

    WindowWidthSizeClass.Expanded -> BaseAppTopBar(
      modifier = modifier,
      hasNavigationIcon = hasNavigationIcon,
      onNavigateBack = onNavigateBack,
      iconSize = 32.dp
    )

    else -> BaseAppTopBar(
      modifier = modifier,
      hasNavigationIcon = hasNavigationIcon,
      onNavigateBack = onNavigateBack
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseAppTopBar(
  modifier: Modifier = Modifier,
  hasNavigationIcon: Boolean = false,
  onNavigateBack: () -> Unit = {},
  iconSize: Dp = 24.dp
) {
  TopAppBar(
    title = {
      Text(text = stringResource(R.string.app_name))
    },
    modifier = modifier,
    navigationIcon = {
      if (hasNavigationIcon) {
        Icon(
          imageVector = ImageVector.vectorResource(R.drawable.arrow_back_24px),
          contentDescription = null,
          modifier = Modifier
            .clickable(onClick = onNavigateBack)
            .size(iconSize)
        )
      }
    }
  )
}

@Composable
fun MusicPlayerAppFloatingActionButton(
  windowSizeClass: WindowSizeClass,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  when (windowSizeClass.widthSizeClass) {
    WindowWidthSizeClass.Compact -> BaseFloatingActionButton(
      onClick = onClick,
      modifier = modifier
    )

    WindowWidthSizeClass.Medium -> BaseFloatingActionButton(
      onClick = onClick,
      modifier = modifier.size(64.dp),
      iconSize = 32.dp
    )

    WindowWidthSizeClass.Expanded -> BaseFloatingActionButton(
      onClick = onClick,
      modifier = modifier.size(80.dp),
      iconSize = 40.dp
    )

    else -> BaseFloatingActionButton(
      onClick = onClick,
      modifier = modifier
    )
  }
}

@Composable
fun BaseFloatingActionButton(
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  iconSize: Dp = 24.dp
) {
  FloatingActionButton(
    onClick = onClick,
    modifier = modifier
  ) {
    Icon(
      imageVector = ImageVector.vectorResource(R.drawable.arrow_forward_24px),
      contentDescription = null,
      modifier = Modifier.size(iconSize)
    )
  }
}
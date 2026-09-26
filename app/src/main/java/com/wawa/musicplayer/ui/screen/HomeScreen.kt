package com.wawa.musicplayer.ui.screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.session.MediaController
import androidx.media3.ui.compose.material3.MiniController
import com.wawa.musicplayer.MIME_TYPE_AUDIO
import com.wawa.musicplayer.media.helper.rememberMediaController
import androidx.core.net.toUri
import com.wawa.musicplayer.R

@Composable
fun HomeScreen(
  modifier: Modifier = Modifier,
  mediaController: MediaController? = rememberMediaController()
) {
  val videoPickerLauncher = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.GetContent()
  ) { uri ->
    uri?.let {
      mediaController?.let { controller ->
        val mediaMetadata = MediaMetadata.Builder()
          .setTitle("Koeru")
          .setArtist("Alexandros")
          .setArtworkUri("android.resource://com.wawa.musicplayer/${R.drawable.koeru}".toUri())
          .build()

        val mediaItem = MediaItem.Builder()
          .setUri(uri)
          .setMediaMetadata(mediaMetadata)
          .build()

        controller.setMediaItem(mediaItem)
        controller.prepare()
      }
    }
  }

  Column(
    modifier = modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Button(
      onClick = {
        videoPickerLauncher.launch(input = MIME_TYPE_AUDIO)
      }
    ) {
      Text(text = "Select audio")
    }

    mediaController?.let { controller ->
      MiniController(
        player = controller
      )
    }
  }
}
package com.wawa.musicplayer.ui.screen.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.wawa.musicplayer.R

enum class TopLevelDestination(
  val graph: Graph,
  @DrawableRes val iconId: Int,
  @StringRes val labelId: Int
) {
  PLAYLIST(
    graph = PlaylistGraph,
    iconId = R.drawable.music_note_2_24px,
    labelId = R.string.playlist_nav
  ),
  UPLOAD(
    graph = UploadGraph,
    iconId = R.drawable.upload_24px,
    labelId = R.string.upload_nav
  );

  companion object {
    fun getByLabelId(@StringRes labelId: Int): TopLevelDestination? =
      entries.find { it.labelId == labelId }
  }
}
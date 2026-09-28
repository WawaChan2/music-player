package com.wawa.musicplayer.ui.screen.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.wawa.musicplayer.R

enum class TopLevelDestination(
  val tab: Tab,
  @DrawableRes val iconId: Int,
  @StringRes val labelId: Int
) {
  PLAYLIST(
    tab = PlaylistTab,
    iconId = R.drawable.music_note_2_24px,
    labelId = R.string.playlist_nav
  ),
  UPLOAD(
    tab = UploadTab,
    iconId = R.drawable.upload_24px,
    labelId = R.string.upload_nav
  )
}
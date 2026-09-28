package com.wawa.musicplayer.ui.screen.navigation

import kotlinx.serialization.Serializable

@Serializable
data class NavigationState(
  val selectedTab: Tab = PlaylistTab,
  val backStackByTab: Map<Tab, List<Screen>> = mapOf(
    PlaylistTab to listOf(Picker),
    UploadTab to listOf(Upload)
  )
)
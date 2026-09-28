package com.wawa.musicplayer.ui.screen.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Tab

@Serializable
data object PlaylistTab : Tab

@Serializable
data object UploadTab : Tab
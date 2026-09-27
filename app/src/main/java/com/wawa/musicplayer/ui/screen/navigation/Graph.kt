package com.wawa.musicplayer.ui.screen.navigation

import kotlinx.serialization.Serializable

sealed interface Graph

@Serializable
data object PlaylistGraph : Graph

@Serializable
data object UploadGraph : Graph
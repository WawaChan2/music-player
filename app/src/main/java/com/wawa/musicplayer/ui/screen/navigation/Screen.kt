package com.wawa.musicplayer.ui.screen.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen

@Serializable
data object Display : Screen

@Serializable
data object Editor : Screen

@Serializable
data object NowPlaying : Screen

@Serializable
data object Upload : Screen
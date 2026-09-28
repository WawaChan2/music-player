package com.wawa.musicplayer.ui.screen.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen

@Serializable
data object Picker : Screen

@Serializable
data object Player : Screen

@Serializable
data object Upload : Screen
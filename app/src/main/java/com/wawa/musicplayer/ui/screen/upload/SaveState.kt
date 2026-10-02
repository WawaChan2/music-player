package com.wawa.musicplayer.ui.screen.upload

sealed interface SaveState

data object SaveLoading: SaveState

data object SaveSuccess : SaveState

data object SaveError : SaveState
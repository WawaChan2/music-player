package com.wawa.musicplayer.ui.screen.upload

sealed interface UploadProcessingState

data object UploadLoading : UploadProcessingState

data object UploadSuccess : UploadProcessingState

data object UploadIncorrectMimeType : UploadProcessingState

data object UploadError : UploadProcessingState


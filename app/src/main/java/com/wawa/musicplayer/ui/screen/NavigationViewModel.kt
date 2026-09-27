package com.wawa.musicplayer.ui.screen

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wawa.musicplayer.data.NavigationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(private val navigationRepository: NavigationRepository) :
  ViewModel() {
  val navigationUiState: StateFlow<NavigationUiState> = navigationRepository.destinationIdFlow.map {
    NavigationUiState(it)
  }.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000),
    initialValue = NavigationUiState()
  )

  fun setDestination(@StringRes destinationId: Int) {
    viewModelScope.launch {
      navigationRepository.setDestinationId(destinationId)
    }
  }
}
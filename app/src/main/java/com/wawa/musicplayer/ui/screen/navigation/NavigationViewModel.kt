package com.wawa.musicplayer.ui.screen.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wawa.musicplayer.data.NavigationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(private val navigationRepository: NavigationRepository) :
  ViewModel() {
  val navigationState: StateFlow<NavigationState> = navigationRepository.navigationStateFlow
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = NavigationState()
    )

  fun selectTab(tab: Tab) {
    val newNavigationState = navigationState.value.copy(selectedTab = tab)

    viewModelScope.launch {
      navigationRepository.saveNavigationState(newNavigationState)
    }
  }

  fun navigateToScreenOnTab(tab: Tab, to: Screen) {
    updateBackStackOnTab(tab) { backstack ->
      backstack + to
    }
  }

  fun navigateBackOnTab(tab: Tab) {
    updateBackStackOnTab(tab) { backStack ->
      backStack.dropLast(1)
    }
  }

  private fun updateBackStackOnTab(tab: Tab, transform: (List<Screen>) -> List<Screen>) {
    val backStackByTab = navigationState.value.backStackByTab
    val backStack = backStackByTab[tab]!!

    val newBackStack = transform(backStack)
    val newBackStackByTab = backStackByTab + (tab to newBackStack)

    val newNavigationState = navigationState.value.copy(backStackByTab = newBackStackByTab)

    viewModelScope.launch {
      navigationRepository.saveNavigationState(newNavigationState)
    }
  }
}
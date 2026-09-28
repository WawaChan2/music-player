package com.wawa.musicplayer.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.wawa.musicplayer.ui.screen.navigation.NavigationState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import javax.inject.Inject

class NavigationRepository @Inject constructor(private val dataStore: DataStore<Preferences>) {
  private val json = Json {
    allowStructuredMapKeys = true
  }

  val navigationStateFlow: Flow<NavigationState> = dataStore.data.map { preferences ->
    preferences[NAVIGATION_STATE_KEY]?.let { json.decodeFromString<NavigationState>(it) }
      ?: NavigationState()
  }

  suspend fun saveNavigationState(navigationState: NavigationState) {
    dataStore.edit { preferences ->
      preferences[NAVIGATION_STATE_KEY] = json.encodeToString(navigationState)
    }
  }

  private companion object {
    val NAVIGATION_STATE_KEY = stringPreferencesKey("navigation_state")
  }
}
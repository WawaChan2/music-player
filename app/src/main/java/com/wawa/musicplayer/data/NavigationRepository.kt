package com.wawa.musicplayer.data

import androidx.annotation.StringRes
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.wawa.musicplayer.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NavigationRepository @Inject constructor(private val dataStore: DataStore<Preferences>) {
  val destinationIdFlow: Flow<Int> = dataStore.data.map { preferences ->
    preferences[DESTINATION_ID] ?: R.string.playlist_nav
  }

  suspend fun setDestinationId(@StringRes destinationId: Int) {
    dataStore.edit { preferences ->
      preferences[DESTINATION_ID] = destinationId
    }
  }

  private companion object {
    val DESTINATION_ID = intPreferencesKey("destination_id")
  }
}
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
  val labelIdFlow: Flow<Int> = dataStore.data.map { preferences ->
    preferences[LABEL_ID] ?: R.string.playlist_nav
  }

  suspend fun setLabelId(@StringRes labelId: Int) {
    dataStore.edit { preferences ->
      preferences[LABEL_ID] = labelId
    }
  }

  private companion object {
    val LABEL_ID = intPreferencesKey("label_id")
  }
}
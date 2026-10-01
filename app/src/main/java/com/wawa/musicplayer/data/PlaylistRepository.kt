package com.wawa.musicplayer.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlaylistRepository @Inject constructor(private val trackDao: TrackDao) {
  fun getAllTracks(): Flow<List<Track>> = trackDao.getAllTracks()
}
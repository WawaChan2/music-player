package com.wawa.musicplayer.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlaylistRepository @Inject constructor(private val trackDao: TrackDao) {
  suspend fun insertTrack(track: Track) = trackDao.insertTrack(track)

  suspend fun updateTrack(track: Track) = trackDao.updateTrack(track)

  suspend fun deleteTrack(track: Track) = trackDao.deleteTrack(track)

  fun getAllTracks(): Flow<List<Track>> = trackDao.getAllTracks()
}
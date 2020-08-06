package com.pustovit.tmdbclient.data.repository.artist.datasource

import com.pustovit.tmdbclient.domain.model.Artist

interface ArtistCacheDataSource {

    suspend fun getArtistFromCache(): List<Artist>

    suspend fun saveArtistToCache(artistList: List<Artist>)
}
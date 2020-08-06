package com.pustovit.tmdbclient.domain.repository

import com.pustovit.tmdbclient.domain.model.Artist

interface ArtistRepository {

    suspend fun getActors(): List<Artist>?

    suspend fun updateArtists(): List<Artist>?
}
package com.pustovit.tmdbclient.domain.usecase.artist

import com.pustovit.tmdbclient.domain.repository.ArtistRepository

class UpdateArtistsUseCase(private val artistRepository: ArtistRepository) {
    suspend fun execute() = artistRepository.updateArtists()
}
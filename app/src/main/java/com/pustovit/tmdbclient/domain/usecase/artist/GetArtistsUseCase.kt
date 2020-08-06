package com.pustovit.tmdbclient.domain.usecase.artist

import com.pustovit.tmdbclient.domain.repository.ArtistRepository

class GetArtistsUseCase(private val actorRepository: ArtistRepository) {
    suspend fun execute() = actorRepository.getActors()
}
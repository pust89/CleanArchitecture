package com.pustovit.tmdbclient.domain.usecase.movie

import com.pustovit.tmdbclient.domain.repository.MovieRepository

class UpdateMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute() = movieRepository.updateMovies()
}
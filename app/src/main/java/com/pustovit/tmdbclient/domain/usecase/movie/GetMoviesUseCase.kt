package com.pustovit.tmdbclient.domain.usecase.movie

import com.pustovit.tmdbclient.domain.repository.MovieRepository

class GetMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute()  = movieRepository.getMovies()
}



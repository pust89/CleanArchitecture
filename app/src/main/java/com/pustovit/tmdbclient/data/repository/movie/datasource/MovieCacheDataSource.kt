package com.pustovit.tmdbclient.data.repository.movie.datasource

import com.pustovit.tmdbclient.domain.model.Movie

interface MovieCacheDataSource {

    suspend fun getMoviesFromCache(): List<Movie>

    suspend fun saveMoviesToCache(movie: List<Movie>)

}
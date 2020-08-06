package com.pustovit.tmdbclient.domain.repository

import com.pustovit.tmdbclient.domain.model.Movie

interface MovieRepository {

    suspend fun getMovies(): List<Movie>?

    suspend fun updateMovies(): List<Movie>?

}
package com.pustovit.tmdbclient.data.repository.movie.datasourseimpl


import com.pustovit.tmdbclient.data.network.dto.ResponsePageMovie
import com.pustovit.tmdbclient.data.network.service.MovieTmdbService
import com.pustovit.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import retrofit2.Response

class MovieRemoteDataSourceImpl constructor(
    private val movieTmdbService: MovieTmdbService,
    private val apiKey: String
) : MovieRemoteDataSource {
    override suspend fun getResponseMovies(): Response<ResponsePageMovie> {
        return movieTmdbService.getPopularMovies(apiKey)
    }
}
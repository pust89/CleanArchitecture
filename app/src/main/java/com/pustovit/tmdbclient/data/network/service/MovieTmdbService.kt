package com.pustovit.tmdbclient.data.network.service

import com.pustovit.tmdbclient.data.network.dto.ResponsePageArtist
import com.pustovit.tmdbclient.data.network.dto.ResponsePageMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieTmdbService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): Response<ResponsePageMovie>

    @GET("movie/popular")
    suspend fun getPopularMoviesWithPaging(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<ResponsePageMovie>

    @GET("person/popular")
    suspend fun getPopularArtists(@Query("api_key") apiKey: String): Response<ResponsePageArtist>
}
package com.pustovit.tmdbclient.data.repository.movie.datasource

import com.pustovit.tmdbclient.data.network.dto.ResponsePageMovie
import retrofit2.Response

interface MovieRemoteDataSource {

    suspend fun getResponseMovies(): Response<ResponsePageMovie>
}
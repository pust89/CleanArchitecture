package com.pustovit.tmdbclient.data.repository.artist.datasourceimpl


import com.pustovit.tmdbclient.data.network.dto.ResponsePageArtist
import com.pustovit.tmdbclient.data.network.service.MovieTmdbService
import com.pustovit.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import retrofit2.Response

class ArtistRemoteDataSourceImpl(
    private val tmbdService: MovieTmdbService,
    private val apiKey: String
) : ArtistRemoteDataSource {

    override suspend fun getResponseArtist(): Response<ResponsePageArtist> {
        return tmbdService.getPopularArtists(apiKey)
    }
}
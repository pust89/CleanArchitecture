package com.pustovit.tmdbclient.data.repository.artist.datasource

import com.pustovit.tmdbclient.data.network.dto.ArtistDto
import com.pustovit.tmdbclient.data.network.dto.ResponsePageArtist
import com.pustovit.tmdbclient.data.network.dto.ResponsePageMovie
import retrofit2.Response

interface ArtistRemoteDataSource {

    suspend fun getResponseArtist(): Response<ResponsePageArtist>


}
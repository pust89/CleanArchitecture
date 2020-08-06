package com.pustovit.tmdbclient.data.repository.artist

import com.pustovit.tmdbclient.LOG_TAG
import com.pustovit.tmdbclient.data.database.entity.artist.DatabaseArtist
import com.pustovit.tmdbclient.data.database.entity.artist.asDomainModel
import com.pustovit.tmdbclient.data.network.dto.ArtistDto
import com.pustovit.tmdbclient.data.network.dto.ResponsePageArtist
import com.pustovit.tmdbclient.data.network.dto.asDatabaseModel
import com.pustovit.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.pustovit.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.pustovit.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.pustovit.tmdbclient.domain.model.Artist
import com.pustovit.tmdbclient.domain.repository.ArtistRepository
import retrofit2.Response
import timber.log.Timber

class ArtistRepositoryImpl(
    private val artistCacheDataSource: ArtistCacheDataSource,
    private val artistLocalDataSource: ArtistLocalDataSource,
    private val artistRemoteDataSource: ArtistRemoteDataSource
) : ArtistRepository {

    override suspend fun getActors(): List<Artist>? {
        return getArtistFromCacheDataSource()
    }

    override suspend fun updateArtists(): List<Artist>? {
        val artistDtoList = getArtistFromRemoteDataSource()
        artistLocalDataSource.deleteAllDatabaseArtists()
        artistLocalDataSource.saveDatabaseArtists(artistDtoList.asDatabaseModel())
        artistCacheDataSource.saveArtistToCache(artistLocalDataSource.getDatabaseArtists().asDomainModel())
        return artistCacheDataSource.getArtistFromCache()
    }

    private suspend fun getArtistFromCacheDataSource(): List<Artist> {
        lateinit var artists: List<Artist>
        try {

            artists = getArtistsFromCacheDataSource(

            )
        } catch (exception: Exception) {
            throw exception
        }

        if (artists.isNotEmpty()) {
            return artists
        } else {
            val databaseArtistList: List<DatabaseArtist> = getArtistsFromLocalDataSource()
            artists = databaseArtistList.asDomainModel()
        }
        return artists
    }

    private suspend fun getArtistsFromCacheDataSource(): List<Artist> {
        return artistCacheDataSource.getArtistFromCache()

    }

    private suspend fun getArtistsFromLocalDataSource(): List<DatabaseArtist> {

        lateinit var databaseArtists: List<DatabaseArtist>

        try {
            databaseArtists = artistLocalDataSource.getDatabaseArtists()
        } catch (exception: Exception) {
            throw exception
        }

        if (databaseArtists.isNotEmpty()) {
            return databaseArtists
        } else {
            val artistDtoList: List<ArtistDto> = getArtistFromRemoteDataSource()

            databaseArtists = artistDtoList.asDatabaseModel()

            artistLocalDataSource.saveDatabaseArtists(databaseArtists)
        }
        return databaseArtists
    }

    private suspend fun getArtistFromRemoteDataSource(): List<ArtistDto> {

        lateinit var artistDtoList: List<ArtistDto>

        try {
            val response: Response<ResponsePageArtist> = artistRemoteDataSource.getResponseArtist()
            val responseCode = response.code()

            Timber.tag(LOG_TAG)
                .i("ArtistRepositoryImpl -> getArtistFromRemoteDataSource() -> responseCod = $responseCode")

            val responsePageArtist = response.body()
            if (responsePageArtist != null) {

                artistDtoList = responsePageArtist.results

            }
        } catch (exception: Exception) {
            throw exception
        }
        return artistDtoList
    }
}
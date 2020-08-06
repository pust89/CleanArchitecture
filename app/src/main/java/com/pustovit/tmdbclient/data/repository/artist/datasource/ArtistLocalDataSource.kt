package com.pustovit.tmdbclient.data.repository.artist.datasource

import com.pustovit.tmdbclient.data.database.entity.artist.DatabaseArtist

interface ArtistLocalDataSource {

    suspend fun getDatabaseArtists(): List<DatabaseArtist>

    suspend fun saveDatabaseArtists(databaseArtistList: List<DatabaseArtist>)

    suspend fun deleteAllDatabaseArtists()

}
package com.pustovit.tmdbclient.data.repository.artist.datasourceimpl

import com.pustovit.tmdbclient.data.database.dao.DatabaseArtistDao
import com.pustovit.tmdbclient.data.database.dao.DatabaseArtistKnownForDao
import com.pustovit.tmdbclient.data.database.dao.DatabaseKnownForDao
import com.pustovit.tmdbclient.data.database.entity.artist.DatabaseArtist
import com.pustovit.tmdbclient.data.database.entity.artist.DatabaseArtistKnownFor
import com.pustovit.tmdbclient.data.database.entity.artist.DatabaseKnownFor
import com.pustovit.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource

class ArtistLocalDataSourceImpl(
    private val databaseArtistDao: DatabaseArtistDao,
    private val databaseKnownForDao: DatabaseKnownForDao,
    private val databaseArtistKnownForDao: DatabaseArtistKnownForDao
) : ArtistLocalDataSource {

    override suspend fun getDatabaseArtists(): List<DatabaseArtist> {
        val databaseArtistList = databaseArtistDao.getArtists()
        databaseArtistList.forEach { databaseArtist ->
            val artistId = databaseArtist.id
            val knownForIds = getArtistKnownForIds(artistId)
            val knownForList = getKnownForListById(knownForIds)
            databaseArtist.knownFor = knownForList
        }
        return databaseArtistList
    }

    private suspend fun getArtistKnownForIds(artistId: Int): List<Int> {
        return databaseArtistKnownForDao.getArtistKnownForIds(artistId)
    }

    private suspend fun getKnownForListById(knownForIds: List<Int>): List<DatabaseKnownFor> {
        return databaseKnownForDao.getKnownForByIds(knownForIds)
    }

    override suspend fun saveDatabaseArtists(databaseArtistList: List<DatabaseArtist>) {
        databaseArtistList.forEach { databaseArtist ->
            saveDatabaseArtist(databaseArtist)
            val artistId = databaseArtist.id
            databaseArtist.knownFor?.forEach {
                saveDatabaseKnownFor(it)
                val knownForId = it.id
                saveDatabaseArtistKnownForDao(artistId, knownForId)
            }
        }
    }

    private suspend fun saveDatabaseArtist(databaseArtist: DatabaseArtist) {
        databaseArtistDao.saveArtists(databaseArtist)
    }

    private suspend fun saveDatabaseKnownFor(databaseKnownFor: DatabaseKnownFor) {
        databaseKnownForDao.saveKnownFor(databaseKnownFor)
    }

    private suspend fun saveDatabaseArtistKnownForDao(artistId: Int, knownForId: Int) {
        databaseArtistKnownForDao.saveArtistKnownFor(
            DatabaseArtistKnownFor(
                artistId = artistId,
                knownForId = knownForId
            )
        )
    }

    override suspend fun deleteAllDatabaseArtists() {
        databaseArtistKnownForDao.deleteAllKnownFor()
        databaseKnownForDao.deleteAllKnownFor()
        databaseArtistDao.deleteAllArtist()
    }

}
package com.pustovit.tmdbclient.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pustovit.tmdbclient.data.database.entity.artist.DatabaseArtist
import com.pustovit.tmdbclient.data.database.entity.artist.DatabaseArtistKnownFor
import com.pustovit.tmdbclient.data.database.entity.artist.DatabaseKnownFor

@Dao
interface DatabaseArtistDao {

    @Query("SELECT * FROM artist_table ORDER BY popularity DESC;")
    suspend fun getArtists(): List<DatabaseArtist>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArtists(databaseArtistList: DatabaseArtist)

    @Query("DELETE FROM artist_table;")
    suspend fun deleteAllArtist()

}

@Dao
interface DatabaseKnownForDao {

    @Query("SELECT * FROM known_for_table WHERE id in (:filterIds) ORDER BY voteAverage DESC;")
    suspend fun getKnownForByIds(filterIds: List<Int>): List<DatabaseKnownFor>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveKnownFor(databaseKnownFor: DatabaseKnownFor)

    @Query("DELETE FROM known_for_table;")
    suspend fun deleteAllKnownFor()

}

@Dao
interface DatabaseArtistKnownForDao {

    @Query("SELECT knownForId FROM artist_known_for_table WHERE artistId =:artistId;")
    suspend fun getArtistKnownForIds(artistId: Int): List<Int>

    @Insert
    suspend fun saveArtistKnownFor(databaseArtistKnownFor: DatabaseArtistKnownFor)

    @Query("DELETE FROM artist_known_for_table;")
    suspend fun deleteAllKnownFor()
}
package com.pustovit.tmdbclient.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pustovit.tmdbclient.data.database.entity.movie.DatabaseMovie



@Dao
interface DatabaseMovieDao {

    @Query("SELECT * FROM movies_table ORDER BY popularity DESC;")
    suspend fun getMovies(): List<DatabaseMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(databaseMovieList: List<DatabaseMovie>)

    @Query("DELETE FROM movies_table;")
    suspend fun deleteAllMovies()

}
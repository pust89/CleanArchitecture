package com.pustovit.tmdbclient.data.repository.movie.datasource

import com.pustovit.tmdbclient.data.database.entity.movie.DatabaseMovie


interface MovieLocalDataSource {

    suspend fun getMoviesFromDatabase(): List<DatabaseMovie>

    suspend fun saveMovieToDatabase(databaseMovieList: List<DatabaseMovie>)

    suspend fun deleteAllDatabaseMovies()

}
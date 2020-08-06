package com.pustovit.tmdbclient.data.repository.movie.datasourseimpl

import com.pustovit.tmdbclient.data.database.dao.DatabaseMovieDao

import com.pustovit.tmdbclient.data.database.entity.movie.DatabaseMovie
import com.pustovit.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource

class MovieLocalDataSourceImpl constructor(private val movieDao: DatabaseMovieDao) :
    MovieLocalDataSource {

    override suspend fun getMoviesFromDatabase(): List<DatabaseMovie> {
        return movieDao.getMovies()
    }

    override suspend fun saveMovieToDatabase(databaseMovieList: List<DatabaseMovie>) {
            movieDao.saveMovies(databaseMovieList)
    }

    override suspend fun deleteAllDatabaseMovies() {
            movieDao.deleteAllMovies()
    }
}
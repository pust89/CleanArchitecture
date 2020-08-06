package com.pustovit.tmdbclient.data.repository.movie.datasourseimpl

import com.pustovit.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.pustovit.tmdbclient.domain.model.Movie

class MovieCacheDataSourceImpl :
    MovieCacheDataSource {
    private val movieCacheList: MutableList<Movie> = ArrayList<Movie>()

    override suspend fun getMoviesFromCache(): List<Movie> {
        return movieCacheList
    }

    override suspend fun saveMoviesToCache(movie: List<Movie>) {
        movieCacheList.clear()
        movieCacheList.addAll(movie)
    }
}
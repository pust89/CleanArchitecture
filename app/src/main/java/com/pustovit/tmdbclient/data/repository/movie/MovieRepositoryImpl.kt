package com.pustovit.tmdbclient.data.repository.movie

import com.pustovit.tmdbclient.LOG_TAG
import com.pustovit.tmdbclient.data.database.entity.movie.DatabaseMovie
import com.pustovit.tmdbclient.data.database.entity.movie.asDomainModel
import com.pustovit.tmdbclient.data.network.dto.MovieDto
import com.pustovit.tmdbclient.data.network.dto.asDatabaseModel
import com.pustovit.tmdbclient.data.network.dto.asDomainModel
import com.pustovit.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.pustovit.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.pustovit.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.pustovit.tmdbclient.domain.model.Movie
import com.pustovit.tmdbclient.domain.repository.MovieRepository
import kotlinx.coroutines.delay
import timber.log.Timber

class MovieRepositoryImpl
constructor(
    private val movieCacheDataSource: MovieCacheDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override suspend fun getMovies(): List<Movie>? {
        delay(1000)
        return getMoviesFromCacheDataSource()
    }


    override suspend fun updateMovies(): List<Movie>? {
        val movieDtoList: List<MovieDto> = getMoviesFromRemoteDataSource()
        movieLocalDataSource.deleteAllDatabaseMovies()
        movieLocalDataSource.saveMovieToDatabase(movieDtoList.asDatabaseModel())
        movieCacheDataSource.saveMoviesToCache(movieDtoList.asDomainModel())
        delay(1000)
        return movieCacheDataSource.getMoviesFromCache()
    }



    private suspend fun getMoviesFromCacheDataSource(): List<Movie> {

        lateinit var movieList: List<Movie>

        try {
            movieList = movieCacheDataSource.getMoviesFromCache()

            Timber.tag(LOG_TAG)
                .i("MovieRepositoryImpl -> getMoviesFromCache() -> movieList.size = ${movieList.size}")
        } catch (exception: Exception) {
            Timber.tag(LOG_TAG).e("MovieRepositoryImpl -> getMoviesFromCache() ->$exception")
            throw exception
        }

        if (movieList.isNotEmpty()) {

            return movieList

        } else {

            val databaseMovieList: List<DatabaseMovie> = getMoviesFromLocalDataSource()

            movieList = databaseMovieList.asDomainModel()

            movieCacheDataSource.saveMoviesToCache(movieList)

        }

        return movieList
    }

    private suspend fun getMoviesFromLocalDataSource(): List<DatabaseMovie> {

        lateinit var databaseMovieList: List<DatabaseMovie>

        try {
            databaseMovieList = movieLocalDataSource.getMoviesFromDatabase()


        } catch (exception: Exception) {
            Timber.tag(LOG_TAG).e("MovieRepositoryImpl -> getMoviesFromDatabase() ->$exception")
            throw exception
        }

        if (databaseMovieList.isNotEmpty()) {

            return databaseMovieList

        } else {

            val movieDtoList: List<MovieDto> = getMoviesFromRemoteDataSource()

            databaseMovieList = movieDtoList.asDatabaseModel()

            movieLocalDataSource.saveMovieToDatabase(databaseMovieList)
        }

        return databaseMovieList
    }

    private suspend fun getMoviesFromRemoteDataSource(): List<MovieDto> {

        lateinit var movieDtoList: List<MovieDto>

        try {
            val response = movieRemoteDataSource.getResponseMovies()
            val responseCode = response.code()

            Timber.tag(LOG_TAG)
                .i("MovieRepositoryImpl -> getMoviesFromRemoteDataSource() -> responseCod = $responseCode")

            val responsePageMovie = response.body()
            if (responsePageMovie != null) {

                movieDtoList = responsePageMovie.results

            }

        } catch (exception: Exception) {
            Timber.tag(LOG_TAG).e("MovieRepositoryImpl -> getMoviesFromRemoteDataSource() ->$exception")
            throw exception
        }

        return movieDtoList
    }

}
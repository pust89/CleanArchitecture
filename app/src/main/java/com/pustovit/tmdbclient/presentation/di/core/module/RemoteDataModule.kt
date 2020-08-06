package com.pustovit.tmdbclient.presentation.di.core.module

import com.pustovit.tmdbclient.data.network.service.MovieTmdbService
import com.pustovit.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.pustovit.tmdbclient.data.repository.artist.datasourceimpl.ArtistRemoteDataSourceImpl
import com.pustovit.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.pustovit.tmdbclient.data.repository.movie.datasourseimpl.MovieRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule(private val apiKey:String) {

    @Provides
    @Singleton
    fun provideMovieRemoteDataSource(
        movieTmdbService: MovieTmdbService
    ): MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(movieTmdbService, apiKey)
    }

    @Provides
    @Singleton
    fun provideArtistRemoteDataSource(
        movieTmdbService: MovieTmdbService
    ): ArtistRemoteDataSource {
        return ArtistRemoteDataSourceImpl(movieTmdbService, apiKey)
    }
}
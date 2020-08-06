package com.pustovit.tmdbclient.presentation.di.core.module

import com.pustovit.tmdbclient.data.repository.artist.ArtistRepositoryImpl
import com.pustovit.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.pustovit.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.pustovit.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.pustovit.tmdbclient.data.repository.movie.MovieRepositoryImpl
import com.pustovit.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.pustovit.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.pustovit.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.pustovit.tmdbclient.domain.repository.ArtistRepository
import com.pustovit.tmdbclient.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideArtistRepository(
        cacheDataSource: ArtistCacheDataSource,
        localDataSource: ArtistLocalDataSource,
        remoteDataSource: ArtistRemoteDataSource
    ): ArtistRepository {
        return ArtistRepositoryImpl(cacheDataSource, localDataSource, remoteDataSource)
    }


    @Provides
    @Singleton
    fun provideMovieRepository(
        cacheDataSource: MovieCacheDataSource,
        localDataSource: MovieLocalDataSource,
        remoteDataSource: MovieRemoteDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(cacheDataSource, localDataSource, remoteDataSource)
    }

}
package com.pustovit.tmdbclient.presentation.di.core.module

import com.pustovit.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.pustovit.tmdbclient.data.repository.artist.datasourceimpl.ArtistCacheDataSourceImpl
import com.pustovit.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.pustovit.tmdbclient.data.repository.movie.datasourseimpl.MovieCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule {

    @Provides
    @Singleton
    fun provideMovieCacheDataSource(): MovieCacheDataSource {
        return MovieCacheDataSourceImpl()
    }

    @Provides
    @Singleton
    fun provideArtistCacheDataSource(): ArtistCacheDataSource {
        return ArtistCacheDataSourceImpl()
    }

}
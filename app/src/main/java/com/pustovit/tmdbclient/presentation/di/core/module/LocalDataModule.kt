package com.pustovit.tmdbclient.presentation.di.core.module

import com.pustovit.tmdbclient.data.database.dao.DatabaseArtistDao
import com.pustovit.tmdbclient.data.database.dao.DatabaseArtistKnownForDao
import com.pustovit.tmdbclient.data.database.dao.DatabaseKnownForDao
import com.pustovit.tmdbclient.data.database.dao.DatabaseMovieDao
import com.pustovit.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.pustovit.tmdbclient.data.repository.artist.datasourceimpl.ArtistLocalDataSourceImpl
import com.pustovit.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.pustovit.tmdbclient.data.repository.movie.datasourseimpl.MovieLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule() {

    @Provides
    @Singleton
    fun provideArtistLocalDataSource(
        databaseArtistDao: DatabaseArtistDao,
        databaseKnownForDao: DatabaseKnownForDao,
        databaseArtistKnownForDao: DatabaseArtistKnownForDao
    ): ArtistLocalDataSource {
        return ArtistLocalDataSourceImpl(
            databaseArtistDao,
            databaseKnownForDao,
            databaseArtistKnownForDao
        )
    }

    @Provides
    @Singleton
    fun provideMovieLocalDataSource(databaseMovieDao: DatabaseMovieDao): MovieLocalDataSource {
        return MovieLocalDataSourceImpl(databaseMovieDao)
    }
}
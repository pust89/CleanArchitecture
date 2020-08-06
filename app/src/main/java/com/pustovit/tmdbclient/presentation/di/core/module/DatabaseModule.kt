package com.pustovit.tmdbclient.presentation.di.core.module

import android.content.Context
import com.pustovit.tmdbclient.data.database.TmdbDatabase
import com.pustovit.tmdbclient.data.database.dao.DatabaseArtistDao
import com.pustovit.tmdbclient.data.database.dao.DatabaseArtistKnownForDao
import com.pustovit.tmdbclient.data.database.dao.DatabaseKnownForDao
import com.pustovit.tmdbclient.data.database.dao.DatabaseMovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule() {

    @Provides
    @Singleton
     fun provideDatabaseArtistDao(tmdbDatabase: TmdbDatabase): DatabaseArtistDao {
        return tmdbDatabase.databaseArtistDao()
    }

    @Provides
    @Singleton
     fun provideDatabaseKnownForDao(tmdbDatabase: TmdbDatabase):DatabaseKnownForDao{
        return tmdbDatabase.databaseDatabaseKnownFor()
    }

    @Provides
    @Singleton
     fun provideDatabaseArtistKnownForDao(tmdbDatabase: TmdbDatabase):DatabaseArtistKnownForDao{
        return tmdbDatabase.databaseArtistKnownForDao()
    }

    @Provides
    @Singleton
     fun provideMovieDao(tmdbDatabase: TmdbDatabase): DatabaseMovieDao {
        return tmdbDatabase.databaseMovieDao()
    }

    @Provides
    @Singleton
     fun provideTmdbDatabase(context: Context): TmdbDatabase {
        return TmdbDatabase.getInstance(context.applicationContext)
    }


}
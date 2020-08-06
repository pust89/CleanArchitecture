package com.pustovit.tmdbclient.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pustovit.tmdbclient.data.database.dao.DatabaseArtistDao
import com.pustovit.tmdbclient.data.database.dao.DatabaseArtistKnownForDao
import com.pustovit.tmdbclient.data.database.dao.DatabaseKnownForDao
import com.pustovit.tmdbclient.data.database.dao.DatabaseMovieDao
import com.pustovit.tmdbclient.data.database.entity.artist.DatabaseArtist
import com.pustovit.tmdbclient.data.database.entity.artist.DatabaseArtistKnownFor
import com.pustovit.tmdbclient.data.database.entity.artist.DatabaseKnownFor
import com.pustovit.tmdbclient.data.database.entity.movie.DatabaseMovie


@Database(
    entities = [DatabaseArtist::class, DatabaseKnownFor::class, DatabaseArtistKnownFor::class, DatabaseMovie::class],
    version = 1,
    exportSchema = false
)
abstract class TmdbDatabase : RoomDatabase() {

    abstract fun databaseArtistDao(): DatabaseArtistDao

    abstract fun databaseDatabaseKnownFor(): DatabaseKnownForDao

    abstract fun databaseArtistKnownForDao(): DatabaseArtistKnownForDao

    abstract fun databaseMovieDao(): DatabaseMovieDao


    companion object {

        @Volatile
        private var INSTANCE: TmdbDatabase? = null

        fun getInstance(context: Context): TmdbDatabase {
            synchronized(TmdbDatabase::class.java) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TmdbDatabase::class.java,
                        "tmdb_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}


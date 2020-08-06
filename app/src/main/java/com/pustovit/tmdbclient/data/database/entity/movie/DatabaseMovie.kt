package com.pustovit.tmdbclient.data.database.entity.movie

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pustovit.tmdbclient.domain.model.Movie

@Entity(tableName = "movies_table")
data class DatabaseMovie(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val posterMediumSizeUrl: String,
    val posterLargeSizeUrl: String,
    var releaseDate: String,
    var popularity: Double
)

fun List<DatabaseMovie>.asDomainModel(): List<Movie> {
    return map {
        Movie(
            it.id,
            it.title,
            it.originalTitle,
            it.overview,
            it.posterMediumSizeUrl,
            it.posterLargeSizeUrl,
            it.releaseDate,
            it.popularity
        )
    }
}


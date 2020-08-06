package com.pustovit.tmdbclient.data.database.entity.artist

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.pustovit.tmdbclient.domain.model.Artist
import com.pustovit.tmdbclient.domain.model.KnownFor

@Entity(tableName = "artist_table")
data class DatabaseArtist(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val popularity: Double,
    val photoMediumSizeUrl: String,
    val photoLargeSizeUrl: String
) {
    @Ignore
    lateinit var knownFor: List<DatabaseKnownFor>
}

@Entity(tableName = "known_for_table")
data class DatabaseKnownFor(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val title: String,
    val mediaType: String,
    val date: String,
    val voteCount: Int,
    val voteAverage: Double
)

@Entity(tableName = "artist_known_for_table")
data class DatabaseArtistKnownFor(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val artistId: Int,
    val knownForId: Int
)

fun List<DatabaseKnownFor>.toDomainModel(): List<KnownFor> {
    return map {
        KnownFor(
            id = it.id,
            title = it.title,
            mediaType = it.mediaType,
            date = it.date,
            voteCount = it.voteCount,
            voteAverage = it.voteAverage

        )
    }
}

fun List<DatabaseArtist>.asDomainModel(): List<Artist> {
    return map { databaseArtist ->
        val artist = Artist(
            id = databaseArtist.id,
            name = databaseArtist.name,
            popularity = databaseArtist.popularity,
            photoMedium = databaseArtist.photoMediumSizeUrl,
            photoLarge = databaseArtist.photoLargeSizeUrl
        ).apply {
            this.knownFor = databaseArtist.knownFor.toDomainModel()
        }
        artist
    }
}
package com.pustovit.tmdbclient.data.network.dto


import com.pustovit.tmdbclient.BuildConfig
import com.pustovit.tmdbclient.data.database.entity.artist.DatabaseArtist
import com.pustovit.tmdbclient.data.database.entity.artist.DatabaseKnownFor
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponsePageArtist(
    @Json(name = "page")
    val page: Int,
    @Json(name = "total_results")
    val totalResults: Int,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "results")
    val results: List<ArtistDto>
)

@JsonClass(generateAdapter = true)
data class ArtistDto(
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "known_for_department")
    val knownForDepartment: String,
    @Json(name = "gender")
    val gender: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "profile_path")
    val profilePath: String,
    @Json(name = "adult")
    val adult: Boolean,
    @Json(name = "known_for")
    val knownFor: List<KnownForDto>,
    @Json(name = "name")
    val name: String
)

@JsonClass(generateAdapter = true)
data class KnownForDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "original_name")
    val originalName: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "original_title")
    val originalTitle: String?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "media_type")
    val mediaType: String?,
    @Json(name = "first_air_date")
    val firstAirDate: String?,
    @Json(name = "release_date")
    val releaseDate: String?,
    @Json(name = "vote_count")
    val voteCount: Int
)

fun List<KnownForDto>.toDatabaseModel(): List<DatabaseKnownFor> {
    return map {
        val id = it.id
        val title = it.title ?: it.name ?: ""
        val mediaType = it.mediaType ?: ""
        val date = it.firstAirDate ?: it.releaseDate ?: ""
        val voteCount = it.voteCount
        val voteAverage = it.voteAverage
        DatabaseKnownFor(id, title, mediaType, date, voteCount, voteAverage)
    }
}

fun ArtistDto.toDatabaseModel(): DatabaseArtist {
    val databaseArtist = DatabaseArtist(
        id = this.id,
        name = this.name,
        popularity = this.popularity,
        photoMediumSizeUrl = BuildConfig.POSTER_BASE_URL + BuildConfig.POSTER_MEDIUM_SIZE_PATH + this.profilePath,
        photoLargeSizeUrl = BuildConfig.POSTER_BASE_URL + BuildConfig.POSTER_LARGE_SIZE_PATH + this.profilePath
    )
    databaseArtist.knownFor = this.knownFor.toDatabaseModel()
    return databaseArtist
}

fun List<ArtistDto>.asDatabaseModel(): List<DatabaseArtist> {
    return map {
        it.toDatabaseModel()
    }
}


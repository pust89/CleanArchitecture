package com.pustovit.tmdbclient.data.network.dto


import com.pustovit.tmdbclient.BuildConfig
import com.pustovit.tmdbclient.data.database.entity.movie.DatabaseMovie
import com.pustovit.tmdbclient.domain.model.Movie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponsePageMovie(
    @Json(name = "page")
    val page: Int,
    @Json(name = "total_results")
    val totalResults: Int,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "results")
    val results: List<MovieDto>
)

@JsonClass(generateAdapter = true)
data class MovieDto(
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "vote_count")
    val voteCount: Int,
    @Json(name = "video")
    val video: Boolean,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "adult")
    val adult: Boolean,
    @Json(name = "backdrop_path")
    val backdropPath: String,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "original_title")
    val originalTitle: String,
    @Json(name = "genre_ids")
    val genreIds: List<Int>,
    @Json(name = "title")
    val title: String,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "release_date")
    val releaseDate: String
)

fun List<MovieDto>.asDomainModel(): List<Movie> {
    return map {
        Movie(
            it.id,
            it.title,
            it.originalTitle,
            it.overview,
            BuildConfig.POSTER_BASE_URL + BuildConfig.POSTER_MEDIUM_SIZE_PATH + it.posterPath,
            BuildConfig.POSTER_BASE_URL + BuildConfig.POSTER_LARGE_SIZE_PATH + it.posterPath,
            it.releaseDate,
            it.popularity
        )
    }
}

fun List<MovieDto>.asDatabaseModel(): List<DatabaseMovie> {
    return map {
        DatabaseMovie(
            it.id,
            it.title,
            it.originalTitle,
            it.overview,
            BuildConfig.POSTER_BASE_URL + BuildConfig.POSTER_MEDIUM_SIZE_PATH + it.posterPath,
            BuildConfig.POSTER_BASE_URL + BuildConfig.POSTER_LARGE_SIZE_PATH + it.posterPath,
            it.releaseDate,
            it.popularity
        )
    }
}

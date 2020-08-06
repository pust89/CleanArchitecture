package com.pustovit.tmdbclient.domain.model


import com.pustovit.tmdbclient.domain.utils.smartTruncate

/**
 * Domain model.
 *
 */
data class Movie(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val posterMediumSizeUrl: String,
    val posterLargeSizeUrl: String,
    var releaseDate: String,
    var popularity: Double
) {
    override fun toString(): String {
        return "Movie(overview='$overview', releaseDate='$releaseDate', popularity=$popularity)"
    }

    val shortOverview: String
        get() = overview.smartTruncate(100)
}

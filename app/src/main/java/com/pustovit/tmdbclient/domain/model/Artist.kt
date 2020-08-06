package com.pustovit.tmdbclient.domain.model

import androidx.room.PrimaryKey
import com.pustovit.tmdbclient.domain.utils.smartTruncate


/**
 * Domain model.
 *
 */
data class Artist(
    val id: Int,
    val name: String,
    val popularity: Double,
    val photoMedium: String,
    val photoLarge: String
){
    lateinit var knownFor: List<KnownFor>
    override fun toString(): String {
        return "Artist(id=$id, name='$name', popularity=$popularity, photoMedium='$photoMedium', photoLarge='$photoLarge', knownFor=$knownFor)"
    }
    var showKnownFor: Boolean = false


}
/**
 * Domain model.
 *
 */
data class KnownFor(
    val id: Int,
    val title: String,
    val mediaType: String,
    val date:String,
    val voteCount: Int,
    val voteAverage: Double
){
    override fun toString(): String {
        return "$title, date: $date, $mediaType\nvoteCount=$voteCount, voteAverage=$voteAverage\n\n"
    }
}


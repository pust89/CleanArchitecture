package com.pustovit.tmdbclient.presentation.common

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pustovit.tmdbclient.R
import com.pustovit.tmdbclient.domain.model.Artist
import com.pustovit.tmdbclient.domain.model.KnownFor
import com.pustovit.tmdbclient.domain.model.Movie
import com.pustovit.tmdbclient.presentation.contentscreen.artistfrag.ArtistAdapter
import com.pustovit.tmdbclient.presentation.contentscreen.moviefrag.MovieAdapter

object BindingAdapters {

    @JvmStatic
    @BindingAdapter(value = ["imageUrl"])
    fun ImageView.setImage(imgUrl: String) {
        Glide.with(this.context)
            .load(imgUrl)
            .error(R.drawable.image_error)
            .placeholder(R.drawable.image_placeholder)
            .into(this)
    }

    @JvmStatic
    @BindingAdapter(value = ["artistRating"])
    fun TextView.setArtistRating(rating: Double) {
        this.text = rating.toString()
        if (rating >= 90) {
            this.setTextColor(ContextCompat.getColor(this.context, R.color.green))
        } else if (rating < 90 && rating >= 50) {
            this.setTextColor(ContextCompat.getColor(this.context, R.color.yellow))
        } else {
            this.setTextColor(ContextCompat.getColor(this.context, R.color.red))
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["movieRating"])
    fun TextView.setMovieRating(rating: Double) {
        this.text = rating.toString()
        if (rating >= 120) {
            this.setTextColor(ContextCompat.getColor(this.context, R.color.green))
        } else if (rating < 120 && rating >= 90) {
            this.setTextColor(ContextCompat.getColor(this.context, R.color.yellow))
        } else {
            this.setTextColor(ContextCompat.getColor(this.context, R.color.red))
        }
    }


    @JvmStatic
    @BindingAdapter(value = ["knownForList"])
    fun TextView.setKnownForList(knownForList: List<KnownFor>) {
        val sb: StringBuilder = java.lang.StringBuilder()
        knownForList.forEach { sb.append(it.toString()) }
        this.text = sb.toString()

    }

    @JvmStatic
    @BindingAdapter(value = ["listMovie"])
    fun bindRecyclerViewMovie(recyclerView: RecyclerView, data: List<Movie>?) {
        val adapter = recyclerView.adapter as MovieAdapter
        adapter.submitList(data)
    }


    @JvmStatic
    @BindingAdapter(value = ["listArtist"])
    fun bindRecyclerViewArtist(recyclerView: RecyclerView, data: List<Artist>?) {
        val adapter = recyclerView.adapter as ArtistAdapter
        adapter.submitList(data)
    }


}
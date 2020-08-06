package com.pustovit.tmdbclient.presentation.contentscreen.moviefrag

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pustovit.tmdbclient.R
import com.pustovit.tmdbclient.databinding.MovieItemBinding
import com.pustovit.tmdbclient.domain.model.Movie
import com.pustovit.tmdbclient.presentation.common.ItemClickListener

class MovieAdapter(private val movieItemClickListener: ItemClickListener<Movie>) :
    ListAdapter<Movie, MovieAdapter.MovieItemVH>(
        MovieDiffUtilItemCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemVH {
        return MovieItemVH.from(
            parent
        )
    }


    override fun onBindViewHolder(holder: MovieItemVH, position: Int) {
        val movie = getItem(position)
        holder.bind(movie, movieItemClickListener)
    }


    companion object MovieDiffUtilItemCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    class MovieItemVH(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            movie: Movie,
            movieItemClickListener: ItemClickListener<Movie>
        ) {
            binding.movie = movie
            binding.movieClickListener = movieItemClickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MovieItemVH {
                val binding: MovieItemBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.movie_item,
                    parent,
                    false
                )
                return MovieItemVH(
                    binding
                )
            }
        }
    }
}



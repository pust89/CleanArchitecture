package com.pustovit.tmdbclient.presentation.contentscreen.artistfrag

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pustovit.tmdbclient.R
import com.pustovit.tmdbclient.databinding.ArtistItemBinding
import com.pustovit.tmdbclient.domain.model.Artist


class ArtistAdapter() :
    ListAdapter<Artist, ArtistAdapter.ArtistItemVH>(
        ArtistDiffUtilItemCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistItemVH {
        return ArtistItemVH.from(parent)
    }


    override fun onBindViewHolder(holder: ArtistItemVH, position: Int) {
        val artist = getItem(position)
        holder.bind(artist)
    }


    companion object ArtistDiffUtilItemCallback : DiffUtil.ItemCallback<Artist>() {
        override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem == newItem
        }

    }

    class ArtistItemVH(private val binding: ArtistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(artist: Artist) {
            binding.artist = artist

            if (!artist.showKnownFor) {
                binding.knownForArtist.visibility = View.GONE
            } else{
                binding.knownForArtist.visibility = View.VISIBLE
            }

            binding.root.setOnClickListener {

                if (binding.knownForArtist.visibility == View.GONE) {
                    binding.knownForArtist.visibility = View.VISIBLE

                } else {
                    binding.knownForArtist.visibility = View.GONE
                }
                artist.showKnownFor = !artist.showKnownFor
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ArtistItemVH {
                val binding: ArtistItemBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.artist_item,
                    parent,
                    false
                )

                return ArtistItemVH(binding)
            }
        }
    }
}
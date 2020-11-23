package com.pustovit.tmdbclient.presentation.contentscreen.moviefrag

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.pustovit.tmdbclient.LOG_TAG
import com.pustovit.tmdbclient.R
import com.pustovit.tmdbclient.databinding.MovieFragmentBinding
import com.pustovit.tmdbclient.domain.model.Movie
import com.pustovit.tmdbclient.presentation.common.BaseFragment
import com.pustovit.tmdbclient.presentation.common.ItemClickListener
import com.pustovit.tmdbclient.presentation.di.Injector
import timber.log.Timber



class MovieFragment : BaseFragment<MovieFragmentBinding, MovieViewModel, MovieViewModelFactory>(
    R.layout.movie_fragment,
    MovieViewModel::class.java
) {

    override fun injectFragment() {
        (activity?.application as Injector).createMovieSubComponent().inject(this)
    }

    override val swipeRefreshLayout: SwipeRefreshLayout?
        get() = binding.swipeContainer


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieViewModel = viewModel
        setupRecyclerView(binding.recyclerView)

        if (swipeRefreshLayout != null) {
            swipeRefreshLayout!!.setOnRefreshListener {
                viewModel.updateMovies()
            }
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {

        val movieAdapter: MovieAdapter =
            MovieAdapter(
                ItemClickListener<Movie> {
                    Toast.makeText(this@MovieFragment.context, it.title, Toast.LENGTH_SHORT).show()
                })

        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
        ) {
            recyclerView.layoutManager = LinearLayoutManager(this.context)
        } else {
            recyclerView.layoutManager = GridLayoutManager(this.context, 2)
        }
        binding.recyclerView.apply {
            this.adapter = movieAdapter
            this.setItemViewCacheSize(12)
        }
    }

}



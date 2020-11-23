package com.pustovit.tmdbclient.presentation.contentscreen.artistfrag

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.pustovit.tmdbclient.App
import com.pustovit.tmdbclient.LOG_TAG
import com.pustovit.tmdbclient.R
import com.pustovit.tmdbclient.databinding.ArtistFragmentBinding
import com.pustovit.tmdbclient.presentation.common.BaseFragment
import com.pustovit.tmdbclient.presentation.di.Injector
import timber.log.Timber

class ArtistFragment : BaseFragment<ArtistFragmentBinding, ArtistViewModel, ArtistViewModelFactory>(
    R.layout.artist_fragment,
    ArtistViewModel::class.java
) {
    private lateinit var artistAdapter: ArtistAdapter

    override fun injectFragment() {
        (activity?.application as Injector).createArtistSubComponent().inject(this)
    }


    override val swipeRefreshLayout: SwipeRefreshLayout?
        get() = binding.swipeContainer


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.artistViewModel = viewModel

        setupRecyclerView(binding.recyclerView)

        if (swipeRefreshLayout != null) {
            swipeRefreshLayout!!.setOnRefreshListener {
                viewModel.updateArtists()
            }
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {

        artistAdapter = ArtistAdapter()

        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
        ) {
            recyclerView.layoutManager = LinearLayoutManager(this.context)
        } else {
            recyclerView.layoutManager = GridLayoutManager(this.context, 2)
        }
        binding.recyclerView.apply {
            this.adapter = artistAdapter
            this.setItemViewCacheSize(5)
        }
    }

}
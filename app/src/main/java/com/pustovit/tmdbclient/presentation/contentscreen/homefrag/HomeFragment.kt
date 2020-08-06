package com.pustovit.tmdbclient.presentation.contentscreen.homefrag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pustovit.tmdbclient.databinding.HomeFragmentBinding

import com.pustovit.tmdbclient.domain.model.ContentType

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeFragmentViewModel
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        binding.viewModel = viewModel
        setupNavigationLiveData()
    }

    private fun setupNavigationLiveData() {
        viewModel.navigateToFragment.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                when (it) {
                    ContentType.MOVIE -> findNavController().navigate(HomeFragmentDirections.actionMainFragmentToMovieFragment())
                    ContentType.ARTIST -> findNavController().navigate(HomeFragmentDirections.actionMainFragmentToArtistFragment())
                }
                viewModel.navigateToFragmentDone()
            }
        })
    }

}
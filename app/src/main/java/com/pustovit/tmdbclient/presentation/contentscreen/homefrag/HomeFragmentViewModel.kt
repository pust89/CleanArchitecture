package com.pustovit.tmdbclient.presentation.contentscreen.homefrag

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pustovit.tmdbclient.domain.model.ContentType

class HomeFragmentViewModel (): ViewModel() {

    private val _navigateToFragment: MutableLiveData<ContentType?> = MutableLiveData()

    val navigateToFragment: LiveData<ContentType?>
    get() = _navigateToFragment

    fun onClickButtonMovie() {
        _navigateToFragment.value = ContentType.MOVIE
    }

    fun onClickButtonArtist() {
        _navigateToFragment.value = ContentType.ARTIST
    }



    fun navigateToFragmentDone() {
        _navigateToFragment.value = null
    }

}
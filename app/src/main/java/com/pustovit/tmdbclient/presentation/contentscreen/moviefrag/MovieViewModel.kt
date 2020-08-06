package com.pustovit.tmdbclient.presentation.contentscreen.moviefrag

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.pustovit.tmdbclient.domain.model.Movie
import com.pustovit.tmdbclient.domain.usecase.movie.GetMoviesUseCase
import com.pustovit.tmdbclient.domain.usecase.movie.UpdateMoviesUseCase
import com.pustovit.tmdbclient.presentation.common.BaseFragmentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MovieViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val updateMoviesUseCase: UpdateMoviesUseCase
) : BaseFragmentViewModel() {

    private val _moviesLiveData: MutableLiveData<List<Movie>?> = MutableLiveData()

    val moviesLiveData: LiveData<List<Movie>?>
        get() = _moviesLiveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            displayLoading(true)
            try {
                var result = getMoviesUseCase.execute()
                _moviesLiveData.postValue(result)
            } catch (e: Exception) {
                displayErrorMessage(e.localizedMessage)
            }
            displayLoading(false)
        }
    }

    fun updateMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            displayLoading(true)
            try {
                var result = updateMoviesUseCase.execute()
                _moviesLiveData.postValue(result)

            } catch (e: Exception) {
                displayErrorMessage(e.localizedMessage)
            }
            displayLoading(false)
        }
    }

    /**
     * Experement here!!!
     *
     */

    private val _moviePagingLiveData: MutableLiveData<PagingData<Movie>> = MutableLiveData()
    val moviePagingLiveData: LiveData<PagingData<Movie>>
        get() = _moviePagingLiveData


}
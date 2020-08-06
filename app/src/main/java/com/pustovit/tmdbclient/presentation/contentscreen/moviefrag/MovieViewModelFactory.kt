package com.pustovit.tmdbclient.presentation.contentscreen.moviefrag

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pustovit.tmdbclient.domain.usecase.movie.GetMoviesUseCase
import com.pustovit.tmdbclient.domain.usecase.movie.UpdateMoviesUseCase

class MovieViewModelFactory(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val updateMoviesUseCase: UpdateMoviesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieViewModel(getMoviesUseCase, updateMoviesUseCase) as T
        } else {
            throw Exception("Unknown viewModel ${modelClass::class.java.canonicalName}")
        }
    }
}
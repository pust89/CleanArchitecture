package com.pustovit.tmdbclient.presentation.di.movie


import com.pustovit.tmdbclient.domain.usecase.movie.GetMoviesUseCase
import com.pustovit.tmdbclient.domain.usecase.movie.UpdateMoviesUseCase
import com.pustovit.tmdbclient.presentation.contentscreen.moviefrag.MovieViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MovieModule() {


    @Provides
    @MovieScope
    fun provideMovieViewModelFactory(
        getMoviesUseCase: GetMoviesUseCase,
        updateMoviesUseCase: UpdateMoviesUseCase
    ): MovieViewModelFactory {
        return MovieViewModelFactory(getMoviesUseCase, updateMoviesUseCase)
    }

}
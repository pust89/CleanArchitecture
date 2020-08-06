package com.pustovit.tmdbclient.presentation.di.movie

import com.pustovit.tmdbclient.presentation.contentscreen.moviefrag.MovieFragment
import dagger.Subcomponent


@Subcomponent(modules = [MovieModule::class])
@MovieScope
interface MovieSubComponent {

    fun inject(movieFragment:MovieFragment)

    @Subcomponent.Factory
    interface Factory{
        fun create():MovieSubComponent
    }
}
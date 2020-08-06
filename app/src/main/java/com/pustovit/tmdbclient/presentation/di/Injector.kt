package com.pustovit.tmdbclient.presentation.di

import com.pustovit.tmdbclient.presentation.di.artist.ArtistSubComponent
import com.pustovit.tmdbclient.presentation.di.movie.MovieSubComponent

interface Injector {

    fun createArtistSubComponent(): ArtistSubComponent
    fun createMovieSubComponent(): MovieSubComponent

}
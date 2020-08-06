package com.pustovit.tmdbclient.presentation.di.core.module

import android.content.Context
import com.pustovit.tmdbclient.presentation.di.artist.ArtistSubComponent
import com.pustovit.tmdbclient.presentation.di.movie.MovieSubComponent

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [ArtistSubComponent::class, MovieSubComponent::class])
class AppModule(private val context: Context) {

    @Provides
    @Singleton
     fun provideApplicationContext(): Context {
        return context.applicationContext
    }
}
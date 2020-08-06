package com.pustovit.tmdbclient.presentation.di.core


import com.pustovit.tmdbclient.presentation.di.artist.ArtistSubComponent
import com.pustovit.tmdbclient.presentation.di.core.module.*
import com.pustovit.tmdbclient.presentation.di.movie.MovieSubComponent

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        RemoteDataModule::class,
        LocalDataModule::class,
        CacheDataModule::class,
        RepositoryModule::class,
        UseCaseModule::class]
)
interface AppComponent {

    fun movieSubComponent(): MovieSubComponent.Factory

    fun artistSubComponent(): ArtistSubComponent.Factory


}
package com.pustovit.tmdbclient

import android.app.Application

import com.pustovit.tmdbclient.presentation.di.Injector
import com.pustovit.tmdbclient.presentation.di.artist.ArtistSubComponent

import com.pustovit.tmdbclient.presentation.di.core.AppComponent
import com.pustovit.tmdbclient.presentation.di.core.DaggerAppComponent
import com.pustovit.tmdbclient.presentation.di.core.module.AppModule
import com.pustovit.tmdbclient.presentation.di.core.module.NetworkModule
import com.pustovit.tmdbclient.presentation.di.core.module.RemoteDataModule
import com.pustovit.tmdbclient.presentation.di.movie.MovieSubComponent

import timber.log.Timber

const val LOG_TAG = "myTag"

class App : Application(), Injector {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        setupTimber()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule(BuildConfig.BASE_URL))
            .remoteDataModule(RemoteDataModule(BuildConfig.API_KEY))
            .build()
    }


    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun createArtistSubComponent(): ArtistSubComponent {
       return appComponent.artistSubComponent().create()
    }

    override fun createMovieSubComponent(): MovieSubComponent {
        return appComponent.movieSubComponent().create()
    }
}
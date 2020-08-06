package com.pustovit.tmdbclient.presentation.di.core.module

import com.pustovit.tmdbclient.domain.repository.ArtistRepository
import com.pustovit.tmdbclient.domain.repository.MovieRepository
import com.pustovit.tmdbclient.domain.usecase.artist.GetArtistsUseCase
import com.pustovit.tmdbclient.domain.usecase.artist.UpdateArtistsUseCase
import com.pustovit.tmdbclient.domain.usecase.movie.GetMoviesUseCase
import com.pustovit.tmdbclient.domain.usecase.movie.UpdateMoviesUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetArtistsUseCase(artistRepository:ArtistRepository): GetArtistsUseCase {
        return GetArtistsUseCase(artistRepository)
    }

    @Provides
    fun provideUpdateArtistsUseCase(artistRepository:ArtistRepository): UpdateArtistsUseCase {
        return UpdateArtistsUseCase(artistRepository)
    }

    @Provides
    fun provideGetMoviesUseCase(movieRepository:MovieRepository): GetMoviesUseCase {
        return GetMoviesUseCase(movieRepository)
    }

    @Provides
    fun provideUpdateMoviesUseCase(movieRepository:MovieRepository): UpdateMoviesUseCase {
        return UpdateMoviesUseCase(movieRepository)
    }
}
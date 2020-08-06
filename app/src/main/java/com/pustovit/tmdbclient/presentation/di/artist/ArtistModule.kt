package com.pustovit.tmdbclient.presentation.di.artist


import com.pustovit.tmdbclient.domain.usecase.artist.GetArtistsUseCase
import com.pustovit.tmdbclient.domain.usecase.artist.UpdateArtistsUseCase
import com.pustovit.tmdbclient.presentation.contentscreen.artistfrag.ArtistViewModelFactory

import dagger.Module
import dagger.Provides



@Module
class ArtistModule() {

    @Provides
    @ArtistScope
    fun provideArtistViewModelFactory(
        getArtistsUseCase: GetArtistsUseCase,
        updateArtistsUseCase: UpdateArtistsUseCase
    ): ArtistViewModelFactory {
        return ArtistViewModelFactory(getArtistsUseCase, updateArtistsUseCase)
    }


}
package com.pustovit.tmdbclient.presentation.contentscreen.artistfrag

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pustovit.tmdbclient.domain.usecase.artist.GetArtistsUseCase
import com.pustovit.tmdbclient.domain.usecase.artist.UpdateArtistsUseCase

class ArtistViewModelFactory(
    private val getArtistsUseCase: GetArtistsUseCase,
    private val updateArtistsUseCase: UpdateArtistsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArtistViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ArtistViewModel(getArtistsUseCase, updateArtistsUseCase) as T
        } else {
            throw Exception("Unknown viewModel ${modelClass::class.java.canonicalName}")
        }
    }
}
package com.pustovit.tmdbclient.presentation.di.artist

import com.pustovit.tmdbclient.presentation.contentscreen.artistfrag.ArtistFragment
import dagger.Subcomponent

@ArtistScope
@Subcomponent(modules = [ArtistModule::class])
interface ArtistSubComponent {

    fun inject(artistFragment: ArtistFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ArtistSubComponent
    }
}

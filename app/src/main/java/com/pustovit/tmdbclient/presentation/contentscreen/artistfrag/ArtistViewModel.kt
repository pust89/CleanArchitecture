package com.pustovit.tmdbclient.presentation.contentscreen.artistfrag

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pustovit.tmdbclient.LOG_TAG
import com.pustovit.tmdbclient.domain.model.Artist
import com.pustovit.tmdbclient.domain.usecase.artist.GetArtistsUseCase
import com.pustovit.tmdbclient.domain.usecase.artist.UpdateArtistsUseCase
import com.pustovit.tmdbclient.presentation.common.BaseFragmentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class ArtistViewModel(
    private val getArtistsUseCase: GetArtistsUseCase,
    private val updateArtistsUseCase: UpdateArtistsUseCase
) : BaseFragmentViewModel() {

    private val _artistsLiveData: MutableLiveData<List<Artist>?> = MutableLiveData()

    val artistsLiveData: LiveData<List<Artist>?>
        get() = _artistsLiveData


    init {
        viewModelScope.launch(Dispatchers.IO) {
            displayLoading(true)
            try {
                var result = getArtistsUseCase.execute()
                _artistsLiveData.postValue(result)
            } catch (e: Exception) {
                displayErrorMessage(e.localizedMessage)
                Timber.tag(LOG_TAG).e(e)
            }
            displayLoading(false)
        }
    }

    fun updateArtists() {
        viewModelScope.launch(Dispatchers.IO) {
            displayLoading(true)
            try {
                var result = updateArtistsUseCase.execute()
                _artistsLiveData.postValue(result)

            } catch (e: Exception) {
                displayErrorMessage(e.localizedMessage)
            }
            displayLoading(false)
        }
    }

}
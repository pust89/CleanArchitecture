package com.pustovit.tmdbclient.presentation.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseFragmentViewModel : ViewModel() {

    /*
        Display error message
     */
    private val _errorMessage = MutableLiveData<String?>()

    val errorMessage: LiveData<String?>
        get() = _errorMessage

    fun displayErrorDone() {
        _errorMessage.postValue(null)
    }

    protected fun displayErrorMessage(message: String?) {
        _errorMessage.postValue(message)
    }


    /*
        Loading feature
     */
    private val _loadingDialog = MutableLiveData<Boolean>()
    val loadingDialog: LiveData<Boolean>
        get() = _loadingDialog

    protected fun displayLoading(b: Boolean) {
        _loadingDialog.postValue(b)
    }


}
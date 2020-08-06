package com.pustovit.tmdbclient.presentation.common

class ItemClickListener<T>(private val itemClickListener: (t: T) -> Unit) {

    fun onClick(t: T) {
        itemClickListener.invoke(t)
    }
}
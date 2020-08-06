package com.pustovit.tmdbclient.data.repository.artist.datasourceimpl

import com.pustovit.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.pustovit.tmdbclient.domain.model.Artist

class ArtistCacheDataSourceImpl : ArtistCacheDataSource {

    private var cacheList: MutableList<Artist> = mutableListOf()

    override suspend fun getArtistFromCache(): List<Artist> {
        return cacheList
    }

    override suspend fun saveArtistToCache(artistList: List<Artist>) {
        cacheList.clear()
        cacheList.addAll(artistList)
    }
}
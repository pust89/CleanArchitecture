package com.pustovit.tmdbclient.presentation.di.core.module

import com.pustovit.tmdbclient.data.network.service.MovieTmdbService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule(private val baseUrl: String) {


    @Provides
    @Singleton
     fun provideMovieTmdbService(retrofit: Retrofit): MovieTmdbService {
        return retrofit.create(MovieTmdbService::class.java)
    }


    @Provides
    @Singleton
     fun provideRetrofit(moshi: Moshi): Retrofit {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        return retrofit
    }

    @Provides
    @Singleton
     fun provideMoshi(): Moshi {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return moshi
    }
}
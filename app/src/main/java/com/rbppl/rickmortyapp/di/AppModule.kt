package com.rbppl.rickmortyapp.di

import com.rbppl.rickmortyapp.data.remote.RickAndMortyApiService.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object AppModule {

    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

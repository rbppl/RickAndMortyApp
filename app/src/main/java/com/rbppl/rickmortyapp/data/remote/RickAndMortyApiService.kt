package com.rbppl.rickmortyapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RickAndMortyApiService {
    const val BASE_URL = "https://rickandmortyapi.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: RickAndMortyApi = retrofit.create(RickAndMortyApi::class.java)

}

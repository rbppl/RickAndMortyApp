package com.rbppl.rickmortyapp.data.remote

import com.rbppl.rickmortyapp.domain.model.Location
import com.rbppl.rickmortyapp.domain.model.Character
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface RickAndMortyApi {
    @GET("/api/character")
    suspend fun getCharacters(): CharacterResponse

    @GET("/api/character/{id}")
    suspend fun getCharacterDetail(@Path("id") characterId: Int): Character

    @GET("/api/location")
    suspend fun getLocations(): LocationResponse

    @GET("/api/location/{id}")
    suspend fun getLocationDetail(@Path("id") locationId: Int): Location

    @GET
    suspend fun getEpisodeDetails(@Url url: String): EpisodeResponse
    @GET
    suspend fun getResidentDetails(@Url url: String): ResidentResponse
}

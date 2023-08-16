package com.rbppl.rickmortyapp.data.repository

import com.rbppl.rickmortyapp.domain.model.Location
import com.rbppl.rickmortyapp.data.remote.RickAndMortyApiService

class LocationsRepositoryImpl(private val apiService: RickAndMortyApiService) : LocationsRepository {
    override suspend fun getLocations(): List<Location> {
        val response = apiService.api.getLocations()
        return response.locations
    }

    override suspend fun getLocationDetail(locationId: Int): Location {
        val response = apiService.api.getLocationDetail(locationId)
        return response
    }
}

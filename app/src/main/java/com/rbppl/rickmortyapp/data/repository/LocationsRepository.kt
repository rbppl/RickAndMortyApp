package com.rbppl.rickmortyapp.data.repository

import com.rbppl.rickmortyapp.domain.model.Location

interface LocationsRepository {
    suspend fun getLocations(): List<Location>
    suspend fun getLocationDetail(locationId: Int): Location
}

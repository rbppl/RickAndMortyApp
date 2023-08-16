package com.rbppl.rickmortyapp.domain

import com.rbppl.rickmortyapp.data.repository.LocationsRepository
import com.rbppl.rickmortyapp.domain.model.Location

class GetLocationsUseCase(private val locationRepository: LocationsRepository) {
    suspend operator fun invoke(): List<Location> {
        return locationRepository.getLocations()
    }
}

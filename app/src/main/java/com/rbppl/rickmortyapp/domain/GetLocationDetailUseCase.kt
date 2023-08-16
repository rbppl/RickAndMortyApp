package com.rbppl.rickmortyapp.domain

import com.rbppl.rickmortyapp.data.repository.LocationsRepository
import com.rbppl.rickmortyapp.domain.model.Location

class GetLocationDetailUseCase(private val locationsRepository: LocationsRepository) {

    suspend operator fun invoke(locationId: Int): Location {
        // Вызовите метод из locationsRepository для получения детальной информации о локации по ее ID
        return locationsRepository.getLocationDetail(locationId)
    }
}

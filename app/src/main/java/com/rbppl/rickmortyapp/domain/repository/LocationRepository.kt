package com.rbppl.rickmortyapp.domain.repository

import com.rbppl.rickmortyapp.domain.model.Location

interface LocationRepository {
    suspend fun getLocations(): List<Location>
    // Другие методы, если необходимо
}
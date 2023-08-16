package com.rbppl.rickmortyapp.presentation.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rbppl.rickmortyapp.domain.GetLocationsUseCase

class LocationsViewModelFactory(private val getLocationsUseCase: GetLocationsUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationsViewModel::class.java)) {
            return LocationsViewModel(getLocationsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

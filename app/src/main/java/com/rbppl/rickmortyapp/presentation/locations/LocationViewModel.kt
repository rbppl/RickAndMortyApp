package com.rbppl.rickmortyapp.presentation.locations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rbppl.rickmortyapp.domain.GetLocationsUseCase
import com.rbppl.rickmortyapp.domain.model.Location
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class LocationsViewModel(
    private val getLocationsUseCase: GetLocationsUseCase
) : ViewModel() {

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    private val _locations = MutableLiveData<List<Location>>()
    val locations: LiveData<List<Location>> = _locations

    init {
        fetchLocations()
    }

    private fun fetchLocations() {
        viewModelScope.launch {
            val locationsList = getLocationsUseCase()
            _locations.value = locationsList
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel() // Отменяем корутины при очистке ViewModel
    }
}

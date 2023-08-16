package com.rbppl.rickmortyapp.presentation.locationdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rbppl.rickmortyapp.domain.GetLocationDetailUseCase
import com.rbppl.rickmortyapp.domain.model.Location
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class LocationDetailViewModel(
    private val getLocationDetailUseCase: GetLocationDetailUseCase
) : ViewModel() {

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    private val _location = MutableLiveData<Location>()
    val location: LiveData<Location> = _location

    fun loadLocationDetail(locationId: Int) {
        viewModelScope.launch {
            val locationDetail = getLocationDetailUseCase(locationId)
            _location.value = locationDetail
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}

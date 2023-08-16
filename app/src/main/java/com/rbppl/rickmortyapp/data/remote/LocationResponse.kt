package com.rbppl.rickmortyapp.data.remote

import com.google.gson.annotations.SerializedName
import com.rbppl.rickmortyapp.domain.model.Location

data class LocationResponse(
    @SerializedName("results")
    val locations: List<Location>
)

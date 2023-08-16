package com.rbppl.rickmortyapp.data.remote

import com.google.gson.annotations.SerializedName

data class EpisodeResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("air_date") val airDate: String
)


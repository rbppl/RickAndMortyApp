package com.rbppl.rickmortyapp.data.remote
data class ResidentResponse(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: ResidentOrigin,
    val location: ResidentLocation,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

data class ResidentOrigin(
    val name: String,
    val url: String
)

data class ResidentLocation(
    val name: String,
    val url: String
)

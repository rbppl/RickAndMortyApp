package com.rbppl.rickmortyapp.domain.model

import java.io.Serializable
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: Origin,
    val image: String,
    val episodes: List<String>, // Используем ArrayList вместо List
    val url: String,
    val created: String
) : Serializable


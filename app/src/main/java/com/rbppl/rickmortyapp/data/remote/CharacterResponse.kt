package com.rbppl.rickmortyapp.data.remote
import com.google.gson.annotations.SerializedName
import com.rbppl.rickmortyapp.domain.model.Character

data class CharacterResponse(
    @SerializedName("results")
    val characters: List<Character>
)

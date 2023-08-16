package com.rbppl.rickmortyapp.domain.repository
import com.rbppl.rickmortyapp.domain.model.Character
interface CharacterRepository {
    suspend fun getCharacters(): List<Character>
    // Другие методы, если необходимо
}
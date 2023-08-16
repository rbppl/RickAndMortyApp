package com.rbppl.rickmortyapp.data.repository

import com.rbppl.rickmortyapp.domain.model.Character

interface CharactersRepository {
    suspend fun getCharacters(): List<Character>
    suspend fun getCharacterDetail(characterId: Int): Character
}

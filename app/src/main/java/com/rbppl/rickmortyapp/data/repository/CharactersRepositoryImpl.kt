package com.rbppl.rickmortyapp.data.repository

import com.rbppl.rickmortyapp.domain.model.Character
import com.rbppl.rickmortyapp.data.remote.RickAndMortyApiService

class CharactersRepositoryImpl(private val apiService: RickAndMortyApiService) : CharactersRepository {
    override suspend fun getCharacters(): List<Character> {
        val response = apiService.api.getCharacters()
        return response.characters
    }

    override suspend fun getCharacterDetail(characterId: Int): Character {
        val response = apiService.api.getCharacterDetail(characterId)
        return response
    }
}

package com.rbppl.rickmortyapp.domain

import com.rbppl.rickmortyapp.data.repository.CharactersRepository import com.rbppl.rickmortyapp.domain.model.Character


class GetCharactersUseCase(private val characterRepository: CharactersRepository) {
    suspend operator fun invoke(): List<Character> {
        return characterRepository.getCharacters()
    }

}

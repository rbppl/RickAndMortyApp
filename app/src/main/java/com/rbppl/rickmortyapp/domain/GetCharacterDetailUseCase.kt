package com.rbppl.rickmortyapp.domain

import com.rbppl.rickmortyapp.data.repository.CharactersRepository
import com.rbppl.rickmortyapp.domain.model.Character

class GetCharacterDetailUseCase(private val charactersRepository: CharactersRepository) {

    suspend operator fun invoke(characterId: Int): Character {
        // Вызовите метод из charactersRepository для получения детальной информации о персонаже по его ID
        return charactersRepository.getCharacterDetail(characterId)
    }
}

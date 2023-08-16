package com.rbppl.rickmortyapp.presentation.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rbppl.rickmortyapp.domain.GetCharactersUseCase

class CharactersViewModelFactory(private val getCharactersUseCase: GetCharactersUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharactersViewModel::class.java)) {
            return CharactersViewModel(getCharactersUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

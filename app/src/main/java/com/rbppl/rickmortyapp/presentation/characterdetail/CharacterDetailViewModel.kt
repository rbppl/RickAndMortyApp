package com.rbppl.rickmortyapp.presentation.characterdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rbppl.rickmortyapp.domain.GetCharacterDetailUseCase
import com.rbppl.rickmortyapp.domain.model.Character
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase
) : ViewModel() {

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character> = _character

    fun loadCharacterDetail(characterId: Int) {
        viewModelScope.launch {
            val characterDetail = getCharacterDetailUseCase(characterId)
            _character.value = characterDetail
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}

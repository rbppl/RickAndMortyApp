package com.rbppl.rickmortyapp.presentation.characters
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rbppl.rickmortyapp.domain.GetCharactersUseCase
import com.rbppl.rickmortyapp.domain.model.Character
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class CharactersViewModel(private val getCharactersUseCase: GetCharactersUseCase) : ViewModel() {

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> = _characters

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            val charactersList = getCharactersUseCase()
            _characters.value = charactersList
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
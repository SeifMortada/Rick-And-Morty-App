package com.seifmortada.applications.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seifmortada.applications.usecase.GetCharactersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {
    private val _charactersUiState: MutableStateFlow<CharactersResultUiState> =
        MutableStateFlow(CharactersResultUiState.Idle)
    val charactersUiState = _charactersUiState.asStateFlow()

    fun getCharacters() {
        viewModelScope.launch {
            _charactersUiState.update { CharactersResultUiState.Loading }
            val characters = getCharactersUseCase()
            _charactersUiState.update { CharactersResultUiState.Success(characters) }
        }
    }
}
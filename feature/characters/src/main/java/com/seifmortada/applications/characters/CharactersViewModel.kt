package com.seifmortada.applications.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seifmortada.applications.domain.usecase.GetAllCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) : ViewModel() {
    private val _charactersUiState: MutableStateFlow<CharactersResultUiState> =
        MutableStateFlow(CharactersResultUiState.Idle)
    val charactersUiState = _charactersUiState.asStateFlow()

    fun getCharacters() {
        viewModelScope.launch {
            _charactersUiState.update { CharactersResultUiState.Loading }
            val characters = getAllCharactersUseCase()
            _charactersUiState.update { CharactersResultUiState.Success(characters) }
        }
    }
}
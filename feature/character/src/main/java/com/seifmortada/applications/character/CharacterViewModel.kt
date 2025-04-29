package com.seifmortada.applications.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seifmortada.applications.domain.usecase.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase
) : ViewModel() {
    private val _characterUiState: MutableStateFlow<CharacterResultUiState> =
        MutableStateFlow(CharacterResultUiState.Idle)
    val characterUiState = _characterUiState.asStateFlow()

    fun getCharacter(id: Int) {
        viewModelScope.launch {
            _characterUiState.update { CharacterResultUiState.Loading }
            val character = getCharacterUseCase(id)
            if (character == null) {
                _characterUiState.update { CharacterResultUiState.Error("Error getting character") }
                return@launch
            }
            _characterUiState.update { CharacterResultUiState.Success(character) }
        }
    }
}
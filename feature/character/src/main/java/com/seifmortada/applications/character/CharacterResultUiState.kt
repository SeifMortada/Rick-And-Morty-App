package com.seifmortada.applications.character

import com.seifmortada.applications.domain.models.Character


sealed interface CharacterResultUiState {
    data object Loading : CharacterResultUiState
    data object Idle : CharacterResultUiState
    data class Error(val message: String) : CharacterResultUiState
    data class Success(val characters: Character? = null) : CharacterResultUiState
}
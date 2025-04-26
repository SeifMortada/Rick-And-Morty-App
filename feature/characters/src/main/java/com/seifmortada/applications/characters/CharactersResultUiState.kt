package com.seifmortada.applications.characters

import com.seifmortada.applications.domain.models.Character


sealed interface CharactersResultUiState {
    data object Loading : CharactersResultUiState
    data object Idle : CharactersResultUiState
    data class Error(val message: String) : CharactersResultUiState
    data class Success(val characters: List<Character> = emptyList()) : CharactersResultUiState
}
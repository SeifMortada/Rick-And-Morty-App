package com.seifmortada.applications.character

import com.seifmortada.applications.domain.models.Character
import com.seifmortada.applications.domain.models.Episode


sealed interface CharacterResultUiState {
    data object Loading : CharacterResultUiState
    data object Idle : CharacterResultUiState
    data class Error(val message: String) : CharacterResultUiState
    data class Success(
        val characters: Character? = null,
        val episodes: List<Episode> = emptyList()
    ) : CharacterResultUiState
}
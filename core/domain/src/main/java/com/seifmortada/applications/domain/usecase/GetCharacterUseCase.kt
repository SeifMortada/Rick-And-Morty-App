package com.seifmortada.applications.domain.usecase

import com.seifmortada.applications.domain.models.Character
import com.seifmortada.applications.domain.repository.CharactersRepository
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(id: Int): Character? {
        var result: Character? = null
        val apiResponse = charactersRepository.getCharacter(id)
        apiResponse.onSuccess {
            result = it
        }.onFailure {
            result = null
        }
        return result
    }
}
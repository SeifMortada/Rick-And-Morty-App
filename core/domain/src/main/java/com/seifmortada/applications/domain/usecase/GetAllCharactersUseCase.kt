package com.seifmortada.applications.domain.usecase

import com.seifmortada.applications.domain.models.Character
import com.seifmortada.applications.domain.repository.CharactersRepository
import org.openjdk.tools.javac.jvm.ByteCodes.ret
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(): List<Character> {
        val apiResponse = charactersRepository.getCharacters()
        var result = listOf<Character>()
        apiResponse.onSuccess {
            result = it
        }.onFailure {
            result = emptyList()
        }
        return result
    }
}
package com.seifmortada.applications.domain.usecase

import com.seifmortada.applications.domain.models.Character
import com.seifmortada.applications.domain.repository.CharactersRepository
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {
     suspend operator fun invoke(): List<Character>{
         return charactersRepository.getCharacters()
    }
}
package com.seifmortada.applications.usecase

import com.seifmortada.applications.domain.Character
import com.seifmortada.applications.repository.CharactersRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {
     suspend operator fun invoke(): List<Character>{
         return charactersRepository.getCharacters()
    }
}
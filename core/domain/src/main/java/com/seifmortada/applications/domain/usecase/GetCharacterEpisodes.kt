package com.seifmortada.applications.domain.usecase

import com.seifmortada.applications.domain.repository.CharactersRepository
import javax.inject.Inject

class GetCharacterEpisodes @Inject constructor(private val repository: CharactersRepository) {
    suspend operator fun invoke(episodesUrl: List<String>) = repository.getCharacterEpisodes(episodesUrl)
}
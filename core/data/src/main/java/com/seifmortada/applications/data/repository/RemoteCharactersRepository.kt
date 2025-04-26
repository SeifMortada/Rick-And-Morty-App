package com.seifmortada.applications.data.repository

import com.seifmortada.applications.data.mapper.toDomainCharacter
import com.seifmortada.applications.data.models.AllCharactersResponse
import com.seifmortada.applications.domain.models.Character
import com.seifmortada.applications.domain.repository.CharactersRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class RemoteCharactersRepository @Inject constructor(private val ktorClient: HttpClient) :
    CharactersRepository {
    override suspend fun getCharacters(): List<Character> {
        return ktorClient.get("character")
            .body<AllCharactersResponse>()
            .results.map { it.toDomainCharacter() }
    }

    override suspend fun getCharacter(id: Int): Character {
        return ktorClient.get("character/$id")
            .body<com.seifmortada.applications.data.models.RemoteCharacter>()
            .toDomainCharacter()
    }
}
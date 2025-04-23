package com.seifmortada.applications.data.repository

import com.seifmortada.applications.domain.Character
import com.seifmortada.applications.network.mapper.toDomainCharacter
import com.seifmortada.applications.network.models.AllCharactersResponse
import com.seifmortada.applications.repository.CharactersRepository
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
}
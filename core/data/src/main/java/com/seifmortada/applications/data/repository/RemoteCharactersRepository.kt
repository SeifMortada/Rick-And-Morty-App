package com.seifmortada.applications.data.repository

import com.seifmortada.applications.data.mapper.toDomainCharacter
import com.seifmortada.applications.data.mapper.toDomainEpisode
import com.seifmortada.applications.data.models.AllCharactersResponse
import com.seifmortada.applications.data.models.RemoteEpisode
import com.seifmortada.applications.domain.ApiOperation
import com.seifmortada.applications.domain.models.Character
import com.seifmortada.applications.domain.models.Episode
import com.seifmortada.applications.domain.repository.CharactersRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteCharactersRepository @Inject constructor(private val ktorClient: HttpClient) :
    CharactersRepository {
    override suspend fun getCharacters(): ApiOperation<List<Character>> {
        return safeApiCall {
            ktorClient.get("character")
                .body<AllCharactersResponse>()
                .results.map { it.toDomainCharacter() }
        }
    }

    override suspend fun getCharacter(id: Int): ApiOperation<Character> {
        return safeApiCall {
            ktorClient.get("character/$id")
                .body<com.seifmortada.applications.data.models.RemoteCharacter>()
                .toDomainCharacter()
        }
    }

    override suspend fun getCharacterEpisodes(episodesUrl: List<String>): ApiOperation<List<Episode>> {
        return safeApiCall {
            val episodesTrimmed = episodesUrl.map { url ->
                url.substringAfterLast("/")
            }
            coroutineScope {
                val remoteEpisodes = episodesTrimmed.map { episodeId ->
                    async { ktorClient.get(urlString = "episode/$episodeId").body<RemoteEpisode>() }
                }
                remoteEpisodes.awaitAll().map { it.toDomainEpisode() }
            }
        }
    }
}

private inline fun <T> safeApiCall(apiCall: () -> T): ApiOperation<T> {
    return try {
        ApiOperation.Success(apiCall())
    } catch (e: Exception) {
        ApiOperation.Failure(e)
    }

}


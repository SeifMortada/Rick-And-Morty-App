package com.seifmortada.applications.domain.repository

import com.seifmortada.applications.domain.ApiOperation
import com.seifmortada.applications.domain.models.Character

interface CharactersRepository {
    suspend fun getCharacters(): ApiOperation<List<Character>>
    suspend fun getCharacter(id: Int): ApiOperation<Character>
}
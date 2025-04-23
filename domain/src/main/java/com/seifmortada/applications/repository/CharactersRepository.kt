package com.seifmortada.applications.repository

import com.seifmortada.applications.domain.Character

interface CharactersRepository {
     suspend fun getCharacters(): List<Character>
}
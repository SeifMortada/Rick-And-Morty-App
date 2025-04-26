package com.seifmortada.applications.domain.repository

import com.seifmortada.applications.domain.models.Character

interface CharactersRepository {
     suspend fun getCharacters(): List<Character>
     suspend fun getCharacter(id:Int):Character
}
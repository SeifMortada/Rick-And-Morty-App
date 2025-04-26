package com.seifmortada.applications.data.mapper

import com.seifmortada.applications.domain.models.Character
import com.seifmortada.applications.domain.models.CharacterGender
import com.seifmortada.applications.domain.models.CharacterStatus
import com.seifmortada.applications.data.models.RemoteCharacter

fun RemoteCharacter.toDomainCharacter(): Character {
    return Character(
        created = created,
        episodesUrl = episode,
        gender = defineCharacterGender(gender),
        id = id,
        imageUrl = image,
        location = location.toDomainLocation(),
        name = name,
        origin = origin.toDomainOrigin(),
        species = species,
        status = getCharacterStatus(status),
        type = type
    )
}

fun RemoteCharacter.defineCharacterGender(gender: String): CharacterGender {
    return when (gender.lowercase()) {
        "male" -> CharacterGender.Male
        "female" -> CharacterGender.Female
        "genderless" -> CharacterGender.GenderLess
        else -> CharacterGender.Unknown
    }
}

fun RemoteCharacter.getCharacterStatus(status: String): CharacterStatus {
    return when (status.lowercase()) {
        "alive" -> CharacterStatus.Alive
        "dead" -> CharacterStatus.Dead
        else -> CharacterStatus.Unknown
    }
}
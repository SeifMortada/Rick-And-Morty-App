package com.seifmortada.applications.network.mapper

import com.seifmortada.applications.domain.Character
import com.seifmortada.applications.network.models.RemoteCharacter

fun RemoteCharacter.RemoteLocation.toDomainLocation(): Character.Location {
    return Character.Location(
        name = name,
        url = url
    )
}
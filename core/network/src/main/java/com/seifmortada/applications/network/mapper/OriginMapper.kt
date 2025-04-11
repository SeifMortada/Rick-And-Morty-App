package com.seifmortada.applications.network.mapper

import com.seifmortada.applications.domain.Character
import com.seifmortada.applications.network.models.RemoteCharacter

fun RemoteCharacter.RemoteOrigin.toDomainOrigin(): Character.Origin {
    return Character.Origin(
        name = name,
        url = url
    )
}
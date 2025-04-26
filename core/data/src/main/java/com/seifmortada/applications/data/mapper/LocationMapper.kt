package com.seifmortada.applications.data.mapper

import com.seifmortada.applications.data.models.RemoteCharacter

fun RemoteCharacter.RemoteLocation.toDomainLocation(): com.seifmortada.applications.domain.models.Character.Location {
    return com.seifmortada.applications.domain.models.Character.Location(
        name = name,
        url = url
    )
}
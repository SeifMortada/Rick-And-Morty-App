package com.seifmortada.applications.data.mapper

import com.seifmortada.applications.data.models.RemoteCharacter

fun RemoteCharacter.RemoteOrigin.toDomainOrigin(): com.seifmortada.applications.domain.models.Character.Origin {
    return com.seifmortada.applications.domain.models.Character.Origin(
        name = name,
        url = url
    )
}
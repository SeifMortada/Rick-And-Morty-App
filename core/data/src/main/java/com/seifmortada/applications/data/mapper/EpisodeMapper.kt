package com.seifmortada.applications.data.mapper

import com.seifmortada.applications.data.models.RemoteEpisode
import com.seifmortada.applications.domain.models.Episode

fun RemoteEpisode.toDomainEpisode(): Episode {
    return Episode(
        id = id,
        name = name,
        episode = episodeCode
    )
}
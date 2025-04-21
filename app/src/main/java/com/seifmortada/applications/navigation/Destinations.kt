package com.seifmortada.applications.navigation

import kotlinx.serialization.Serializable

object Destinations {
    @Serializable
    object Characters

    @Serializable
    data class CharacterDetails(val characterId: Int)
}
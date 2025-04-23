package com.seifmortada.applications.character.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.seifmortada.applications.character.CharacterDetailsRoute
import com.seifmortada.applications.network.KtorClient
import kotlinx.serialization.Serializable


@Serializable
data class CharacterDetails(val characterId: Int)

fun NavController.navigateToCharacterDetails(id: Int) =
    navigate(CharacterDetails(id))

fun NavGraphBuilder.characterDetailsScreen(ktorClient: KtorClient) {
    composable<CharacterDetails> {
        val args = it.toRoute<CharacterDetails>()
        CharacterDetailsRoute(ktorClient, args.characterId)
    }
}
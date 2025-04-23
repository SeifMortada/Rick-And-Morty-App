package com.seifmortada.applications.characters.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.seifmortada.applications.characters.CharactersRoute
import kotlinx.serialization.Serializable


@Serializable
object Characters

fun NavController.navigateToCharacters() =
    navigate(Characters)

fun NavGraphBuilder.charactersScreen(
    onCharacterClicked: (Int) -> Unit
) {
    composable<Characters> {
        CharactersRoute(onCharacterClicked = onCharacterClicked)
    }
}
package com.seifmortada.applications.characters.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.seifmortada.applications.characters.CharactersRoute
import com.seifmortada.applications.domain.Character
import kotlinx.serialization.Serializable


@Serializable
object Characters

fun NavController.navigateToCharacters() =
    navigate(Characters)

fun NavGraphBuilder.charactersScreen(
    characters: List<Character>,
    onCharacterClicked: (Int) -> Unit
) {
    composable<Characters> {
        CharactersRoute(characters,onCharacterClicked)
    }
}
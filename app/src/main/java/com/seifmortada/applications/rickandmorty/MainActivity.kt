package com.seifmortada.applications.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.seifmortada.applications.character.CharacterScreen
import com.seifmortada.applications.domain.Character
import com.seifmortada.applications.network.KtorClient
import com.seifmortada.applications.characters.CharactersRoute
import com.seifmortada.applications.navigation.Destinations
import com.seifmortada.applications.rickandmorty.ui.theme.RickAndMortyTheme

class MainActivity : ComponentActivity() {
    private val ktor = KtorClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var characters by remember { mutableStateOf<List<Character>?>(null) }
            LaunchedEffect(true) {
                characters = ktor.getAllCharacters()
            }
            RickAndMortyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { contentPadding ->
                    AppNavigation(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(contentPadding),
                        characters = characters ?: emptyList(),
                        ktorClient = ktor
                    )
                }
            }
        }
    }
}

@Composable
fun AppNavigation(
    characters: List<Character>,
    ktorClient: KtorClient,
    navController: NavHostController = rememberNavController(),
    modifier: Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destinations.Characters
    ) {
        composable<Destinations.Characters> {
            CharactersRoute(characters = characters) {
                navController.navigate(Destinations.CharacterDetails(it))
            }
        }
        composable<Destinations.CharacterDetails> {
            val args = it.toRoute<Destinations.CharacterDetails>()
            CharacterScreen(ktorClient, args.characterId)
        }
    }
}
package com.seifmortada.applications.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.seifmortada.applications.character.navigation.characterDetailsScreen
import com.seifmortada.applications.character.navigation.navigateToCharacterDetails
import com.seifmortada.applications.domain.models.Character
import com.seifmortada.applications.characters.navigation.Characters
import com.seifmortada.applications.characters.navigation.charactersScreen
import com.seifmortada.applications.rickandmorty.ui.theme.RickAndMortyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background
                ) { contentPadding ->
                    RickAndMortyApp(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(contentPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun RickAndMortyApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Characters
    ) {
        charactersScreen(
            onCharacterClicked = { navController.navigateToCharacterDetails(it) }
        )
        characterDetailsScreen()
    }
}
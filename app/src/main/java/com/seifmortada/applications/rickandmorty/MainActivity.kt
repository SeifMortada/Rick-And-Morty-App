package com.seifmortada.applications.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.seifmortada.applications.domain.Character
import com.seifmortada.applications.network.KtorClient
import com.seifmortada.applications.characters.CharactersRoute
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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    characters?.let {
                        com.seifmortada.applications.characters.CharactersRoute(
                            it,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

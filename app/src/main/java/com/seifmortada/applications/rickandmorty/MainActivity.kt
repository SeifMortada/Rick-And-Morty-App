package com.seifmortada.applications.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.seifmortada.applications.network.Character
import com.seifmortada.applications.network.KtorClient
import com.seifmortada.applications.rickandmorty.ui.theme.RickAndMortyTheme

class MainActivity : ComponentActivity() {
    private val ktor = KtorClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var character by remember { mutableStateOf<Character?>(null) }
            LaunchedEffect(true) {
                character = ktor.getCharacter(1)
            }
            RickAndMortyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Text(
                        text = "Hello ${character?.name}!",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

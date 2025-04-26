package com.seifmortada.applications.character

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.seifmortada.applications.character.components.CharacterStatusComponent
import kotlinx.coroutines.delay
import com.seifmortada.applications.domain.models.Character

@Composable
fun CharacterDetailsRoute(
    viewModel: CharacterViewModel = hiltViewModel(),
    characterId: Int
) {
    val uiState by viewModel.characterUiState.collectAsStateWithLifecycle()

    LaunchedEffect(characterId) {
        viewModel.getCharacter(characterId)
        delay(500)
    }
    CharacterDetailsScreen(uiState)


}

@Composable
fun CharacterDetailsScreen(
    state: CharacterResultUiState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (state) {
            is CharacterResultUiState.Error -> Toast.makeText(
                LocalContext.current,
                state.message,
                Toast.LENGTH_SHORT
            ).show()

            CharacterResultUiState.Idle -> Unit
            CharacterResultUiState.Loading -> CircularProgressIndicator()
            is CharacterResultUiState.Success -> {
                val character = state.characters
                val characterDataPoints: List<DataPoint> by remember {
                    derivedStateOf {
                        buildList {
                            character?.let {
                                add(DataPoint("Last Known Location", it.location.name))
                                add(DataPoint("Species", it.species))
                                add(DataPoint("Gender", it.gender.name))
                                it.type.takeIf { type -> type.isNotEmpty() }?.let { type ->
                                    add(DataPoint("Type", type))
                                }
                                add(DataPoint("Origin", it.location.name))
                                add(DataPoint("Episode count", it.episodesUrl.size.toString()))
                            }
                        }
                    }
                }
                CharacterDetailsCard(character!!, characterDataPoints)
            }
        }
    }
}

@Composable
fun CharacterDetailsCard(character: Character, characterDataPoints: List<DataPoint>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        item { CharacterStatusComponent(characterStatus = character.status) }
        item {
            Text(
                text = character.name,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        item { CharacterImage(character.imageUrl) }
        items(characterDataPoints) {
            SmallMagentaText(it.title)
            Spacer(Modifier.height(4.dp))
            MediumWhiteText(it.desc)
            Spacer(Modifier.height(22.dp))
        }
    }
}

@Composable
fun SmallMagentaText(text: String) {
    Text(text = text, style = MaterialTheme.typography.headlineSmall, color = Color.White)
}

@Composable
fun MediumWhiteText(text: String) {
    Text(text = text, style = MaterialTheme.typography.bodyLarge, color = Color.LightGray)
}

@Composable
fun CharacterImage(imageUrl: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize()
                .padding(12.dp)
        ) {
            AsyncImage(
                model = imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
        }
    }
}

@Preview
@Composable
private fun CharacterScreenPreview() {
    val character = Character(
        id = 1,
        name = "Rick Sanchez",
        status = com.seifmortada.applications.domain.models.CharacterStatus.Alive,
        created = "2017-11-04T18:48:46.250Z",
        gender = com.seifmortada.applications.domain.models.CharacterGender.Male,
        imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        location = Character.Location(
            name = "Citadel of Ricks",
            url = ""
        ),
        origin = Character.Origin(
            name = "Earth (C-137)",
            url = ""
        ),
        species = "Human",
        episodesUrl = listOf(
            "https://rickandmortyapi.com/api/episode/1",
            "https://rickandmortyapi.com/api/episode/2",
            "https://rickandmortyapi.com/api/episode/3"
        ),
        type = ""
    )
    val dataPoints = listOf(
        DataPoint("Last Known Location", "Earth (C-137)"),
        DataPoint("Species", "Human"),
        DataPoint("Gender", "Male"),
        DataPoint("Type", ""),
        DataPoint("Origin", "Earth (C-137)"),
        DataPoint("Episode count", "3")
    )
    CharacterDetailsScreen(
        state = CharacterResultUiState.Success(
            characters = character
        )
    )
}

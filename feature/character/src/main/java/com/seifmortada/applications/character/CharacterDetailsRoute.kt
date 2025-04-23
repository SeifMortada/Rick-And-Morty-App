package com.seifmortada.applications.character

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.seifmortada.applications.character.components.CharacterStatusComponent
import com.seifmortada.applications.domain.Character
import com.seifmortada.applications.domain.CharacterGender
import com.seifmortada.applications.domain.CharacterStatus
import com.seifmortada.applications.network.KtorClient
import kotlinx.coroutines.delay

@Composable
fun CharacterDetailsRoute(
    ktorClient: KtorClient,
    characterId: Int
) {
    var character by remember { mutableStateOf<Character?>(null) }
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
    LaunchedEffect(characterId) {
        character = ktorClient.getCharacter(characterId)
        delay(500)
    }
    character?.let {
        CharacterDetailsScreen(it, characterDataPoints)
    }

}

@Composable
fun CharacterDetailsScreen(
    character: Character,
    characterDataPoints: List<DataPoint> = emptyList()
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        item { CharacterStatusComponent(characterStatus = character.status) }
        item { Text(text = character.name , style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.onBackground) }
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
    CharacterDetailsScreen(
        character = Character(
            id = 1,
            name = "Rick Sanchez",
            status = CharacterStatus.Alive,
            created = "2017-11-04T18:48:46.250Z",
            gender = CharacterGender.Male,
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            location = Character.Location(name = "Citadel of Ricks", url = ""),
            origin = Character.Origin(name = "Earth (C-137)", url = ""),
            species = "Human",
            episodesUrl = listOf(
                "https://rickandmortyapi.com/api/episode/1",
                "https://rickandmortyapi.com/api/episode/2",
                "https://rickandmortyapi.com/api/episode/3"
            ),
            type = ""
        ),
        characterDataPoints = listOf(
            DataPoint("Last Known Location", "Earth (C-137)"),
            DataPoint("Species", "Human"),
            DataPoint("Gender", "Male"),
            DataPoint("Type", ""),
            DataPoint("Origin", "Earth (C-137)"),
            DataPoint("Episode count", "3")
        )
    )
}

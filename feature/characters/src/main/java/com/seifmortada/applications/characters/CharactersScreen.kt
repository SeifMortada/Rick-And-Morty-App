package com.seifmortada.applications.characters


import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.seifmortada.applications.domain.models.Character
import com.seifmortada.applications.domain.models.CharacterGender
import com.seifmortada.applications.domain.models.CharacterStatus


@Composable
fun CharactersRoute(
    viewModel: CharactersViewModel = hiltViewModel(),
    onCharacterClicked: (Int) -> Unit
) {
    val uiState = viewModel.charactersUiState.collectAsStateWithLifecycle()
    LaunchedEffect(true) {
        viewModel.getCharacters()
    }
    CharactersScreen(uiState.value, onCharacterClicked)

}

@Composable
fun CharactersScreen(uiState: CharactersResultUiState, onCharacterClicked: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (uiState) {
            is CharactersResultUiState.Error -> Toast.makeText(
                LocalContext.current,
                uiState.message,
                Toast.LENGTH_SHORT
            ).show()

            CharactersResultUiState.Idle -> Unit
            CharactersResultUiState.Loading -> CircularProgressIndicator()
            is CharactersResultUiState.Success -> CharactersList(
                uiState.characters,
                onCharacterClicked
            )
        }
    }
}

@Composable
fun CharactersList(
    characters: List<Character>,
    onCharacterClicked: (Int) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(12.dp)
    ) {
        items(characters) {
            CharacterCard(it, onCharacterClicked)
        }
    }
}

@Composable
fun CharacterCard(
    character: Character,
    onItemClicked: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(250.dp)
            .clickable {
                onItemClicked(character.id)
            }
            .padding(12.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = character.name,
                style = MaterialTheme.typography.headlineSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(4.dp))
            AsyncImage(
                model = character.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Preview
@Composable
private fun ScreenPreview() {
    CharactersScreen(
        CharactersResultUiState.Success(
            characters = listOf(
               Character(
                    id = 1,
                    name = "Rick Sanchez",
                    imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                    status = CharacterStatus.Alive,
                    species = "Human",
                    gender = CharacterGender.Male,
                    origin = Character.Origin(
                        name = "Earth",
                        url = "https://rickandmortyapi.com/api/location/1"
                    ),
                    location = Character.Location(
                        name = "Earth",
                        url = "https://rickandmortyapi.com/api/location/20"
                    ),
                    created = "2017-11-04T18:48:46.250Z",
                    episodesUrl = listOf(""),
                    type = ""
                )
            )
        )
    ){}
}
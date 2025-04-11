package com.seifmortada.applications.characters


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.seifmortada.applications.domain.Character

@Composable
fun CharactersRoute(
    characters: List<Character>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize().padding(6.dp),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(12.dp)
    ) {
        items(characters) {
            CharacterCard(it)
        }
    }
}

@Composable
fun CharacterCard(character: Character, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = character.name)
            Spacer(Modifier.height(12.dp))
            AsyncImage(
                model = character.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Spacer(Modifier.height(22.dp))
        }
    }
}

@Preview
@Composable
private fun ScreenPreview() {
    /*CharactersRoute(
    )*/
}
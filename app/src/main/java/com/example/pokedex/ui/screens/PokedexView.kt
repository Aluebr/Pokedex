package com.example.pokedex.ui.screens

import com.example.pokedex.domain.models.PokemonEntry
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.pokedex.R
import com.example.pokedex.ui.viewmodels.PokemonViewModel


@Composable
fun PokedexView(viewModel: PokemonViewModel, navController: NavHostController) {
    val pokedex by viewModel.pokedex.observeAsState(initial = emptyList())
val scrollState = rememberLazyGridState()
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "PokeDex", fontSize = 50.sp)
        LazyVerticalGrid(
            state = scrollState,
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(pokedex, key = { it.entryNumber }) { pokemonEntry ->
                PokemonCard(pokemonEntry, viewModel, navController)
            }
        }

    }

}

@Composable
fun PokemonCard(
    pokemonEntry: PokemonEntry,
    viewModel: PokemonViewModel,
    navController: NavHostController
) {
    val pokemonDetails by viewModel.getPokemonDetails(pokemonEntry.entryNumber.toString())
        .observeAsState()

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                if (pokemonDetails == null) {
                    return@clickable
                } else {

                    navController.navigate("PokemonDetailView/${pokemonEntry.entryNumber}")
                }
            },
        colors = CardDefaults.cardColors(
            viewModel.getColorForType(
                pokemonDetails?.types?.get(0) ?: "UNKNOWN"
            ), Color.Black
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = String.format("#%03d", pokemonEntry.entryNumber)
            )
            Text(
                text = pokemonEntry.pokemonSpecies.name.capitalize(),
                modifier = Modifier.padding(top = 8.dp),
                fontSize = 13.sp
            )

            val imagePainter = if (pokemonDetails != null) {
                rememberAsyncImagePainter(pokemonDetails!!.imageURL)
            } else {
                painterResource(id = R.drawable.pokeball)
            }

            Image(
                painter = imagePainter,
                contentDescription = pokemonDetails?.name ?: "Pokeball",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
        }
    }
}


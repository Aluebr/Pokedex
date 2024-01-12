package com.example.pokedex.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pokedex.ui.components.RoundedStatBar
import com.example.pokedex.ui.components.TopBar
import com.example.pokedex.ui.theme.DarkGrey
import com.example.pokedex.ui.theme.Fighting
import com.example.pokedex.ui.theme.Fire
import com.example.pokedex.ui.theme.Flying
import com.example.pokedex.ui.theme.Poison
import com.example.pokedex.ui.theme.Water
import com.example.pokedex.ui.viewmodels.PokemonViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun PokemonDetailView(viewModel: PokemonViewModel, name: String, navController: NavController) {

    viewModel.getPokemonByName(name)
    val pokemon by viewModel.pokemon.observeAsState()

    val statusBarColor = viewModel.getColorForType(pokemon?.types?.get(0) ?: "")

    val systemUiController = rememberSystemUiController()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        systemUiController.setStatusBarColor(statusBarColor)
        TopBar(id = "#${pokemon?.id}", viewModel, pokemon) { navController.popBackStack() }

        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(0.dp, 0.dp, 30.dp, 30.dp))
                .background(DarkGrey)
                .height(200.dp)
        ) {


            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        viewModel.getColorForType(
                            pokemon?.types
                                ?.get(0)
                                .toString()
                        ),
                    ),
                model = pokemon?.imageURL,
                contentDescription = pokemon?.name
            )
        }

        Text(
            text = pokemon?.name ?: "",
            fontSize = 30.sp,
            modifier = Modifier.padding(top = 25.dp, bottom = 25.dp)
        )

        val typesFiltered = pokemon?.types?.toList()

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .offset(10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            if (typesFiltered != null) {
                items(typesFiltered.size) { index ->

                    Box(
                        contentAlignment = Alignment.Center, modifier = Modifier
                            .fillMaxWidth()
                            .width(140.dp)
                            .background(
                                viewModel.getColorForType(typesFiltered[index]),
                                shape = RoundedCornerShape(50)
                            )

                    ) {
                        Text(
                            text = typesFiltered[index],
                            modifier = Modifier.padding(
                                start = 10.dp,
                                end = 10.dp,
                                top = 5.dp,
                                bottom = 5.dp
                            )
                        )
                    }
                    Spacer(modifier = Modifier.size(20.dp))
                }
            }
        }
        Spacer(modifier = Modifier.size(25.dp))



        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = pokemon?.weight ?: "",
                    fontSize = 30.sp
                )
                Text(
                    text = "Weight",
                    color = Color.LightGray
                )
            }
            Spacer(modifier = Modifier.width(40.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = pokemon?.height ?: "",
                    fontSize = 30.sp
                )
                Text(
                    text = "Height",
                    color = Color.LightGray
                )
            }
        }

        Spacer(modifier = Modifier.size(15.dp))

        Text(
            text = "Base Stats",
            fontSize = 30.sp
        )

        RoundedStatBar(value = pokemon?.stats?.get("hp") ?: 0, color = Color.Green, stat = "HP")
        RoundedStatBar(value = pokemon?.stats?.get("attack") ?: 0, color = Fighting, stat = "Atk")
        RoundedStatBar(value = pokemon?.stats?.get("defense") ?: 0, color = Water, stat = "Def")
        RoundedStatBar(
            value = pokemon?.stats?.get("special-attack") ?: 0,
            color = Fire,
            stat = "SpA"
        )
        RoundedStatBar(
            value = pokemon?.stats?.get("special-defense") ?: 0,
            color = Poison,
            stat = "SpD"
        )
        RoundedStatBar(
            value = pokemon?.stats?.get("speed") ?: 0,
            color = Flying,
            stat = "Spe"
        )
    }

}






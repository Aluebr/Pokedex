package com.example.pokedex.view

import androidx.compose.foundation.Canvas
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.ui.theme.DarkGrey
import com.example.pokedex.ui.theme.Fighting
import com.example.pokedex.ui.theme.Fire
import com.example.pokedex.ui.theme.Flying
import com.example.pokedex.ui.theme.Poison
import com.example.pokedex.ui.theme.Water


import com.example.pokedex.viewmodel.PokemonViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun PokemonDetailView(viewModel: PokemonViewModel, name:String,navController: NavController) {

    viewModel.getPokemonByName(name)
    val pokemon by viewModel.pokemon.observeAsState()

    val statusBarColor = viewModel.getColorForType(pokemon?.types?.get(0) ?: "")

    val systemUiController = rememberSystemUiController()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        systemUiController.setStatusBarColor(statusBarColor)
        TopBar(id = "#${pokemon?.id}", viewModel, pokemon)

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
@Composable
fun RoundedStatBar(value: Int, color: Color, stat: String) {
    val maxStatValue = 300
    val statPercentage = (value / maxStatValue.toFloat())

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 50.dp, end = 50.dp, top = 5.dp, bottom = 5.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stat,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(50.dp)

        )
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)

        ) {

            drawRoundRect(
                color = Color.White,
                size = size,
                cornerRadius = CornerRadius(10.dp.toPx(), 10.dp.toPx())
            )

            drawRoundRect(
                color = color,
                size = Size(size.width * statPercentage, size.height),
                cornerRadius = CornerRadius(10.dp.toPx(), 10.dp.toPx())
            )

            val textPaint = Paint().asFrameworkPaint()
            textPaint.textSize = 14.sp.toPx()
            textPaint.textAlign = android.graphics.Paint.Align.CENTER

            val textX = size.width / 2
            val textY = size.height / 2 + textPaint.textSize / 2

            drawContext.canvas.nativeCanvas.drawText(
                "$value / $maxStatValue",
                textX,
                textY,
                textPaint
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(id: String, viewModel: PokemonViewModel, pokemon: Pokemon?) {
    //val pokemon by viewModel.pokemon.observeAsState()
    //viewModel.getPokemonByName(POKEMON_NAME)
    TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(containerColor= viewModel.getColorForType(
        pokemon?.types?.get(0) ?:  ""
    ) ),
        title = {
            Text(
                text = "Pokedex",
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    /*TODO*/
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        },
        actions = {
            Text(
                text = id,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color.Black,
                modifier = Modifier.padding(end = 16.dp)
            )
        }
    )

}


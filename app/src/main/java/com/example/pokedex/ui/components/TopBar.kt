package com.example.pokedex.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.domain.models.Pokemon
import com.example.pokedex.ui.viewmodels.PokemonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    id: String,
    viewModel: PokemonViewModel,
    pokemon: Pokemon?,
    onBackClicked: () -> Boolean
) {

    TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(
        containerColor = viewModel.getColorForType(
            pokemon?.types?.get(0) ?: ""
        )
    ),
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
                    onBackClicked()
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
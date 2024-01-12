package com.example.pokedex
import androidx.hilt.navigation.compose.hiltViewModel
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokedex.ui.theme.PokedexTheme
import com.example.pokedex.view.PokedexView
import com.example.pokedex.view.PokemonDetailView
import com.example.pokedex.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PokedexApp : Application() {}

    @AndroidEntryPoint
    class MainActivity : ComponentActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                PokedexTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            val pokemonViewModel: PokemonViewModel = hiltViewModel()
                            val navController = rememberNavController()

                            NavHost(
                                navController = navController,
                                startDestination = "pokedexView"
                            ) {
                                composable("pokedexView") {
                                    PokedexView(
                                        pokemonViewModel,
                                        navController
                                    ) // Pasar navController si es necesario
                                }
                                composable("PokemonDetailView/{pokemonName}") { backStackEntry ->
                                    val pokemonName =
                                        backStackEntry.arguments?.getString("pokemonName")
                                    PokemonDetailView(
                                        pokemonViewModel,
                                        pokemonName!!,
                                        navController
                                    )
                                }
                            }

                        }

                    }
                }
            }
        }
    }






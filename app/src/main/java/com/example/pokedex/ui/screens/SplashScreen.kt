package com.example.pokedex.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pokedex.R
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavHostController) {

    val splashScreenDuration = 4000L


    LaunchedEffect(true) {
        delay(splashScreenDuration)

        navController.navigate("PokedexView")
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        Image(
            painter = painterResource(id = R.drawable.pokemon),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        )
    }
}
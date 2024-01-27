package com.example.pokedex.data.sources.remote


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




object RetrofitPokedexClient {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"



    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: PokemonApiService by lazy {
        retrofit.create(PokemonApiService::class.java)
    }
}
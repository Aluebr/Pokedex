package com.example.pokedex.data.repository

import PokemonEntry
import com.example.pokedex.data.PokedexApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class PokedexRetrofit {
    private val apiService: PokedexApiService = RetrofitPokedexClient.instance.create(
        PokedexApiService::class.java)

    object RetrofitPokedexClient {
        private const val BASE_URL = "https://pokeapi.co/api/v2/"

        val instance: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
    fun getPokemonList(): List<PokemonEntry> {
        val call = apiService.getPokedex()
        val response = call.execute() // Sincrónico, no recomendado para la UI thread
        if (response.isSuccessful) {
            val pokedex = response.body()
            return pokedex?.pokemonEntries ?: emptyList()
        } else {
            throw Exception("Error al obtener la lista de Pokémon: ${response.errorBody()?.string()}")
        }
    }
}
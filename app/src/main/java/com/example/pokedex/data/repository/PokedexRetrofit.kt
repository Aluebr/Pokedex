package com.example.pokedex.data.repository

import com.example.pokedex.domain.models.PokemonEntry
import com.example.pokedex.data.PokedexApiService


class PokedexRetrofit {
    private val apiService: PokedexApiService = RetrofitPokedexClient.provideRetrofit().create(
        PokedexApiService::class.java)


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
package com.example.pokedex.data.repository

import com.example.pokedex.domain.models.PokemonEntry
import com.example.pokedex.data.sources.remote.PokedexApiService
import com.example.pokedex.data.sources.remote.PokemonApiService


class PokedexRetrofit {
    private val apiService: PokemonApiService = RetrofitPokedexClient.provideRetrofit().create(
        PokemonApiService::class.java
    )


    fun getPokemonList(): List<PokemonEntry> {

        val call = apiService.getPokedex()
        val response = call.execute()
        if (response.isSuccessful) {
            val pokedex = response.body()
            return pokedex?.pokemonEntries ?: emptyList()
        } else {
            throw Exception(
                "Error al obtener la lista de Pokémon: ${
                    response.errorBody()?.string()
                }"
            )
        }

    }
}
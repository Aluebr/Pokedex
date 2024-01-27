package com.example.pokedex.data.repository

import com.example.pokedex.data.sources.remote.RetrofitPokedexClient
import com.example.pokedex.domain.models.Pokedex
import com.example.pokedex.mappers.DTOPokemonToModel
import com.example.pokedex.domain.models.Pokemon
import com.example.pokedex.domain.repositories.IPokemonRepository
import javax.inject.Inject


class PokemonAPIRepositoryImpl @Inject constructor(): IPokemonRepository {

    override fun getPokemonByID(name: String): Pokemon {
        val call = RetrofitPokedexClient.api.getPokemonByName(name)

        val response = call.execute()
        if (response.isSuccessful) {
            val pokemonSerialized = response.body() ?: throw Exception("Respuesta sin cuerpo")

            return DTOPokemonToModel(pokemonSerialized)
        } else {
            throw Exception("Error al obtener datos del Pokémon desde la API")
        }
    }

      override fun getPokemonList(): Pokedex {
        val call = RetrofitPokedexClient.api.getPokedex()

        val response = call.execute()
        if(response.isSuccessful){
            val pokedex = response.body() ?: throw Exception ("Respuesta sin cuerpo")
            return Pokedex(pokedex.pokemonEntries)
        } else {
            throw Exception("Error al obtener datos del Pokémon desde la API")
        }
    }


}
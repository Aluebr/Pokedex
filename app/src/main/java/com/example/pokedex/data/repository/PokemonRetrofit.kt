package com.example.pokedex.data.repository

import com.example.pokedex.mappers.DTOPokemonToModel
import com.example.pokedex.domain.models.Pokemon


class PokemonRetrofit : PokemonRepository {

    override fun getPokemonByID(name: String): Pokemon {
        val call = RetrofitPokemonClient.service.getPokemonByName(name)

        val response = call.execute()
        if (response.isSuccessful) {
            val pokemonSerialized = response.body() ?: throw Exception("Respuesta sin cuerpo")

            return DTOPokemonToModel(pokemonSerialized)
        } else {
            throw Exception("Error al obtener datos del Pokémon desde la API")
        }
    }

}
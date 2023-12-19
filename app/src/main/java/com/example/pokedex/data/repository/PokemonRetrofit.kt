package com.example.pokedex.data.repository

import android.app.Application
import com.example.pokedex.data.PokemonApiService
import com.example.pokedex.data.model.Pokemon
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PokemonRetrofit(aplication:Application) : PokemonRepository {

    override fun getPokemonByName(name: String): Pokemon {
        val call = RetrofitPokemonClient.service.getPokemonByName(name)

        val response = call.execute()
        if (response.isSuccessful) {
            val pokemonSerialized = response.body() ?: throw Exception("Respuesta sin cuerpo")

            val id = pokemonSerialized.id.padStart(3, '0')
            val name = pokemonSerialized.name.replaceFirstChar { it.uppercaseChar() }
            val weight = "${pokemonSerialized.weight / 10} KG"
            val height = "${pokemonSerialized.height / 10} M"
            val imageURL = pokemonSerialized.sprites.other.officialArtwork.frontDefault

            val statsMap = pokemonSerialized.stats.associate {
                it.stat.name to it.base_stat
            }.toMutableMap()

            val typesList = pokemonSerialized.types.map {
                it.type.name
            }.toMutableList()

            return Pokemon(id, name, imageURL, statsMap, typesList, weight, height)
        } else {
            throw Exception("Error al obtener datos del Pokémon desde la API")
        }
    }

    override fun getPokemonById(id: String): Pokemon {
        TODO("Not yet implemented")
    }
}
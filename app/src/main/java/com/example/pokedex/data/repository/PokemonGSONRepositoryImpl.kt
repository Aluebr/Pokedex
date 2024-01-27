package com.example.pokedex.data.repository

import android.app.Application
import com.example.pokedex.domain.models.Pokemon
import com.example.pokedex.data.dto.PokemonDTO
import com.example.pokedex.domain.models.Pokedex
import com.example.pokedex.domain.repositories.IPokemonRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.InputStreamReader
import javax.inject.Inject

class PokemonGSONRepositoryImpl @Inject constructor(val application: Application) : IPokemonRepository {

    val context = application.applicationContext
    override fun getPokemonByID(name: String): Pokemon {


        val inputStream = context.assets.open("$name.json")
        val reader = InputStreamReader(inputStream)

        val gson = Gson()

        val gsonPokemon = gson.fromJson(reader, PokemonDTO::class.java)
        val id = gsonPokemon.id.padStart(3, '0')
        val name = gsonPokemon.name.replaceFirstChar { it.uppercaseChar() }
        var weight = "${gsonPokemon.weight / 10} KG"


        var height = "${gsonPokemon.height / 10} M"
        val imageURL = gsonPokemon.sprites.other.officialArtwork.frontDefault

        val statsMap = mutableMapOf<String, Int>()
        for (stat in gsonPokemon.stats) {
            statsMap[stat.stat.name] = stat.base_stat
        }

        val typesList = mutableListOf<String>()
        for (type in gsonPokemon.types) {
            typesList.add(type.type.name)
        }

        return Pokemon(id, name, imageURL, statsMap, typesList, weight, height)


    }

    override fun getPokemonList(): Pokedex {
        val json = InputStreamReader(context.assets.open("pokedex.json"))
        val gsonBuilder = GsonBuilder()

        val gson = gsonBuilder.create()

        val gsonPokedex = gson.fromJson(json, Pokedex::class.java)
        return Pokedex(gsonPokedex.pokemonEntries)
    }

}
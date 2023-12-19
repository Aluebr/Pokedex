package com.example.pokedex.data.repository

import android.app.Application
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.data.model.PokemonSerialized
import com.google.gson.Gson
import java.io.InputStreamReader

class PokemonGSON(val application: Application) : PokemonRepository {
    override fun getPokemonByName(name: String): Pokemon {

        val context = application.applicationContext
        val inputStream = context.assets.open("$name.json")
        val reader = InputStreamReader(inputStream)

        val gson = Gson()

        val gsonPokemon = gson.fromJson(reader, PokemonSerialized::class.java)
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

    override fun getPokemonById(id: String): Pokemon {
        TODO("Not yet implemented")
    }


}
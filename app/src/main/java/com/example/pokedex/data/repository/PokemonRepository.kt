package com.example.pokedex.data.repository

import com.example.pokedex.data.model.Pokemon

interface PokemonRepository {
    fun getPokemonByName(name: String): Pokemon
    fun getPokemonById(id:String): Pokemon
}
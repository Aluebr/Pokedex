package com.example.pokedex.domain.repositories

import com.example.pokedex.domain.models.Pokedex
import com.example.pokedex.domain.models.Pokemon

interface PokemonRepository {
    fun getPokemonByID(name: String): Pokemon

   // fun getPokemonList(): Pokedex

}

package com.example.pokedex.domain.repositories

import com.example.pokedex.domain.models.Pokedex
import com.example.pokedex.domain.models.Pokemon

interface IPokemonRepository {
    fun getPokemonByID(name: String): Pokemon

    fun getPokemonList(): Pokedex

}

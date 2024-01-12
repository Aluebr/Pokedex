package com.example.pokedex.data.repository

import com.example.pokedex.domain.models.Pokemon

interface PokemonRepository {
    fun getPokemonByID(name: String): Pokemon

}
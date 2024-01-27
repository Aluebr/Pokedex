package com.example.pokedex.data.repository

import com.example.pokedex.domain.models.Pokedex
import com.example.pokedex.domain.models.Pokemon
import com.example.pokedex.domain.repositories.IPokemonRepository
import javax.inject.Inject

class PokemonFallbackRepositoryImpl @Inject constructor(
    private val pokemonGSONRepositoryImpl: PokemonGSONRepositoryImpl,
    private val pokemonAPIRepositoryImpl: PokemonAPIRepositoryImpl
): IPokemonRepository {



    override fun getPokemonByID(name: String): Pokemon {
        return try {
            pokemonAPIRepositoryImpl.getPokemonByID(name)
        }catch (e: Exception) {
            return pokemonGSONRepositoryImpl.getPokemonByID("ditto")
        }
    }

    override fun getPokemonList(): Pokedex {
        return try {
            pokemonAPIRepositoryImpl.getPokemonList()
        }catch (e: Exception) {
            pokemonGSONRepositoryImpl.getPokemonList()
        }
    }

}
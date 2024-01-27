package com.example.pokedex.domain.usecases

import com.example.pokedex.domain.models.Pokedex
import com.example.pokedex.domain.repositories.IPokemonRepository
import javax.inject.Inject

class GetPokedexUseCase @Inject constructor(
    private val repository: IPokemonRepository
) {

    fun getList(): Pokedex {
        return repository.getPokemonList()
    }

}
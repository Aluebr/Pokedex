package com.example.pokedex.domain.usecases

import com.example.pokedex.domain.models.Pokemon
import com.example.pokedex.domain.repositories.IPokemonRepository
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    private val repository: IPokemonRepository
) {

     fun getPokemonInfo(id: String): Pokemon {
        return repository.getPokemonByID(id)
    }

}


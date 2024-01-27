package com.example.pokedex.mappers

import com.example.pokedex.domain.models.Pokemon
import com.example.pokedex.data.dto.PokemonDTO

fun DTOPokemonToModel(pokemonDTO: PokemonDTO): Pokemon {
    val id = pokemonDTO.id.padStart(3, '0')
    val name = pokemonDTO.name.replaceFirstChar { it.uppercaseChar() }
    val weight = "${pokemonDTO.weight / 10} KG"
    val height = "${pokemonDTO.height / 10} M"
    val imageURL = pokemonDTO.sprites.other.officialArtwork.frontDefault

    val statsMap = pokemonDTO.stats.associate {
        it.stat.name to it.base_stat
    }.toMutableMap()

    val typesList = pokemonDTO.types.map {
        it.type.name
    }.toMutableList()

    return Pokemon(id, name, imageURL, statsMap, typesList, weight, height)

}
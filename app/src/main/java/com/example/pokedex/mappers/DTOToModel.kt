package com.example.pokedex.mappers

import com.example.pokedex.domain.models.Pokemon
import com.example.pokedex.domain.models.PokemonSerialized

fun DTOPokemonToModel(pokemonSerialized: PokemonSerialized): Pokemon {
    val id = pokemonSerialized.id.padStart(3, '0')
    val name = pokemonSerialized.name.replaceFirstChar { it.uppercaseChar() }
    val weight = "${pokemonSerialized.weight / 10} KG"
    val height = "${pokemonSerialized.height / 10} M"
    val imageURL = pokemonSerialized.sprites.other.officialArtwork.frontDefault

    val statsMap = pokemonSerialized.stats.associate {
        it.stat.name to it.base_stat
    }.toMutableMap()

    val typesList = pokemonSerialized.types.map {
        it.type.name
    }.toMutableList()

    return Pokemon(id, name, imageURL, statsMap, typesList, weight, height)

}
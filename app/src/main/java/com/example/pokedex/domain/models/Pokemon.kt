package com.example.pokedex.domain.models

import com.google.gson.annotations.SerializedName

data class Pokemon(
    val id: String,
    val name: String,
    val imageURL: String,
    val stats: Map<String, Int>,
    val types: MutableList<String>,
    val weight: String,
    val height: String
)



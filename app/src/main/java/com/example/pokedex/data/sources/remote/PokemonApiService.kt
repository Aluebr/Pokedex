package com.example.pokedex.data.sources.remote

import com.example.pokedex.data.dto.PokemonSerialized
import com.example.pokedex.domain.models.Pokedex
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {
    @GET("pokemon/{name}")
    fun getPokemonByName(@Path("name") name: String): Call<PokemonSerialized>

    @GET("pokedex/national")
    fun getPokedex(): Call<Pokedex>

}
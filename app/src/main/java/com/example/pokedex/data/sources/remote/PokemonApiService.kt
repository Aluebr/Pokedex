package com.example.pokedex.data.sources.remote

import com.example.pokedex.data.dto.PokemonDTO
import com.example.pokedex.domain.models.Pokedex
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface PokemonApiService {

    @Headers(
        "Accept: application/json"
    )
    @GET("pokemon/{name}")
    fun getPokemonByName(@Path("name") name: String): Call<PokemonDTO>

    @Headers(
        "Accept: application/json"
    )
    @GET("pokedex/national")
    fun getPokedex(): Call<Pokedex>

}
package com.example.pokedex.data

import com.example.pokedex.data.model.PokemonSerialized
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {
    @GET("pokemon/{name}")
    fun getPokemonByName(@Path("name") name: String): Call<PokemonSerialized>

}
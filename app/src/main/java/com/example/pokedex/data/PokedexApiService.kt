package com.example.pokedex.data

import com.example.pokedex.domain.models.Pokedex
import retrofit2.Call
import retrofit2.http.GET


interface PokedexApiService {
    @GET("pokedex/national")
    fun getPokedex(): Call<Pokedex>
}
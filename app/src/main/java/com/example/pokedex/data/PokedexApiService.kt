package com.example.pokedex.data

import Pokedex
import retrofit2.Call
import retrofit2.http.GET


interface PokedexApiService {
    @GET("pokedex/national")
    fun getPokedex(): Call<Pokedex>
}
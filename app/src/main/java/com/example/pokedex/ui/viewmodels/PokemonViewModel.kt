package com.example.pokedex.ui.viewmodels

import com.example.pokedex.domain.models.PokemonEntry
import android.app.Application
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.repository.PokedexRetrofit
import com.example.pokedex.domain.models.Pokemon
import com.example.pokedex.data.repository.PokemonAPIRepositoryImpl
import com.example.pokedex.domain.models.ColorTypes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(application: Application) : AndroidViewModel(application){
    private val _pokedex = MutableLiveData<List<PokemonEntry>>()
    val pokedex: LiveData<List<PokemonEntry>> = _pokedex
    private val _pokemon = MutableLiveData<Pokemon>()
    var pokemon: LiveData<Pokemon> = _pokemon
    val pokedexApi = PokedexRetrofit()
    val pokemonApi = PokemonAPIRepositoryImpl()
    private val pokemonDetailsMap = mutableMapOf<String, LiveData<Pokemon>>()
    init {
        getPokedex()
    }

    fun getPokemonByName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val pokemonData = pokemonApi.getPokemonByID(name)
                _pokemon.postValue(pokemonData)
            } catch (e: Exception) {
                _pokemon.postValue(null)
            }
        }
    }

    fun getPokedex(){
        viewModelScope.launch (Dispatchers.IO){
            _pokedex.postValue(pokedexApi.getPokemonList())
        }
    }
    fun getColorForType(type: String): Color {
        val typeEnum = try {
            ColorTypes.valueOf(type.uppercase())
        } catch (e: IllegalArgumentException) {
            ColorTypes.UNKNOWN
        }
        return typeEnum.color
    }
    fun getPokemonDetails(name: String): LiveData<Pokemon> {
        return pokemonDetailsMap.getOrPut(name) {
            MutableLiveData<Pokemon>().also { liveData ->
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val details = pokemonApi.getPokemonByID(name)
                        liveData.postValue(details)
                    } catch (e: Exception) {
                        liveData.postValue(null)
                    }
                }
            }
        }
    }

}
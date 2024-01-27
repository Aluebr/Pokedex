package com.example.pokedex.ui.viewmodels


import android.util.Log
import androidx.compose.ui.graphics.Color

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.repository.PokemonGSONRepositoryImpl
import com.example.pokedex.domain.models.Pokemon
import com.example.pokedex.domain.models.ColorTypes
import com.example.pokedex.domain.models.Pokedex
import com.example.pokedex.domain.usecases.GetPokedexUseCase
import com.example.pokedex.domain.usecases.GetPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val pokemonUseCase: GetPokemonUseCase,
    private val pokedexUseCase: GetPokedexUseCase
) : ViewModel() {

    private val _pokedex = MutableLiveData<Pokedex>()
    val pokedex: LiveData<Pokedex> = _pokedex
    private val _pokemon = MutableLiveData<Pokemon>()
    var pokemon: LiveData<Pokemon> = _pokemon
    private val pokemonDetailsMap = mutableMapOf<String, LiveData<Pokemon>>()

    init {
        getPokedex()
    }

    fun getPokemonByName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val pokemonData = pokemonUseCase.getPokemonInfo(name)
                _pokemon.postValue(pokemonData)
            } catch (e: Exception) {
                _pokemon.postValue(null)
                Log.e("PokemonViewModel", "Error al obtener datos del Pokémon", e)
            }
        }
    }

    fun getPokedex() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val pokedexData = pokedexUseCase.getList()
                _pokedex.postValue(pokedexData)
            } catch (e: Exception) {
                _pokedex.postValue(null)
                Log.e("PokemonViewModel", "Error al obtener la Pokédex", e)
            }
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
                        val details = pokemonUseCase.getPokemonInfo(name)
                        liveData.postValue(details)
                    } catch (e: Exception) {
                        liveData.postValue(null)
                    }
                }
            }
        }
    }
}

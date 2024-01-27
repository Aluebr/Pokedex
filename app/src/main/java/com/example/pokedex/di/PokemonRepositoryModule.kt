package com.example.pokedex.di

import com.example.pokedex.data.repository.PokemonFallbackRepositoryImpl
import com.example.pokedex.domain.repositories.IPokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class PokemonRepositoryModule {

    @Binds
    abstract fun bindRepository(
        pokemonFallbackRepositoryImpl: PokemonFallbackRepositoryImpl
    ): IPokemonRepository
}
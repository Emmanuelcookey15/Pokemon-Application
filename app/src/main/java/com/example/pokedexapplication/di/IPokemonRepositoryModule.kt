package com.example.pokedexapplication.di

import com.example.pokedexapplication.data.repository.PokemonRepositoryImpl
import com.example.pokedexapplication.domain.repo.IPokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class IPokemonRepositoryModule {

    @Binds
    abstract fun bindIPokemonRepository(
        pokemonRepositoryImpl: PokemonRepositoryImpl
    ): IPokemonRepository

}
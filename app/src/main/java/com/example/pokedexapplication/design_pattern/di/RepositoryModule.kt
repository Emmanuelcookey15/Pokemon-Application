package com.example.pokedexapplication.design_pattern.di

import com.example.data.repository.PokemonRepositoryImpl
import com.example.domain.repo.IPokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindIPokemonRepository(
        pokemonRepositoryImpl: PokemonRepositoryImpl
    ): IPokemonRepository

}
package com.example.presentation.di

import com.example.domain.repo.IPokemonRepository
import com.example.domain.usecase.PokemonUseCase
import com.example.presentation.ui.pokemon_list.PokemonListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Provides
    fun providePokemonListViewModel(
        pokemonUseCase: PokemonUseCase
    ) = PokemonListViewModel(pokemonUseCase)


    @Singleton
    @Provides
    fun providePokemonUseCase(
        repository: IPokemonRepository
    ) = PokemonUseCase(repository)
}
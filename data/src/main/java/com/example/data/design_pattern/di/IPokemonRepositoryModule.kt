package com.example.data.design_pattern.di

import com.example.data.repository.PokemonRepositoryImpl
import com.example.domain.repo.IPokemonRepository
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
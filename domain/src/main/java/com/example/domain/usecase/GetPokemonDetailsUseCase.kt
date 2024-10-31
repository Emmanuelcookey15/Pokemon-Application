package com.example.domain.usecase

import com.example.domain.entities.PokemonEntity
import com.example.domain.repo.IPokemonRepository
import com.example.domain.utils.Resource

class GetPokemonDetailsUseCase(
    private val repository: IPokemonRepository
){
    suspend fun execute(pokemonName: String): Resource<PokemonEntity> {
        return repository.getPokemonInfo(pokemonName)
    }
}
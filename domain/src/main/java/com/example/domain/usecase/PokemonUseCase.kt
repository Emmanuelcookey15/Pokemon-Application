package com.example.domain.usecase

import com.example.domain.entities.PokemonEntity
import com.example.domain.entities.PokemonListEntity
import com.example.domain.repo.IPokemonRepository
import com.example.domain.utils.Resource

class PokemonUseCase(
    private val repository: IPokemonRepository
){
    suspend fun executePokemonList(limit: Int, offset: Int,): Resource<List<PokemonListEntity>> {
        return repository.getPokemonList(limit, offset)
    }

    suspend fun executePokemonDetail(pokemonName: String): Resource<PokemonEntity> {
        return repository.getPokemonInfo(pokemonName)
    }
}
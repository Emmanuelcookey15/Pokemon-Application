package com.example.domain.usecase

import com.example.domain.entities.PokemonEntity
import com.example.domain.entities.PokemonDataEntity
import com.example.domain.entities.PokemonListEntity
import com.example.domain.repo.IPokemonRepository
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

class PokemonUseCase(
    private val repository: IPokemonRepository
){
    suspend fun executePokemonList(limit: Int, offset: Int,): Flow<Resource<PokemonListEntity>> {
        return repository.getPokemonList(limit, offset)
    }

    suspend fun executePokemonDetail(pokemonName: String): Flow<Resource<PokemonEntity>> {
        return repository.getPokemonInfo(pokemonName)
    }
}
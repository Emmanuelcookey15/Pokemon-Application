package com.example.domain.usecase

import com.example.domain.entities.PokemonListEntity
import com.example.domain.repo.IPokemonRepository
import com.example.domain.utils.Resource

class GetPokemonListUseCase(
    private val repository: IPokemonRepository
){
    suspend fun execute(limit: Int, offset: Int,): Resource<PokemonListEntity> {
        return repository.getPokemonList(limit, offset)
    }
}
package com.example.domain.repo

import com.example.domain.entities.PokemonEntity
import com.example.domain.entities.PokemonDataEntity
import com.example.domain.entities.PokemonListEntity
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IPokemonRepository {

    suspend fun getPokemonList(
       limit: Int,
       offset: Int,
    ): Flow<Resource<PokemonListEntity>>

    suspend fun getPokemonInfo(
       pokemonName: String
    ): Flow<Resource<PokemonEntity>>
}
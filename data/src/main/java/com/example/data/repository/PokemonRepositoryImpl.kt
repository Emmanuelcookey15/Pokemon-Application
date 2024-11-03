package com.example.data.repository

import com.example.data.mapper.PokemonRepositoryMapper.mapFromPokemonListResponse
import com.example.data.mapper.PokemonRepositoryMapper.mapFromPokemonResponse
import com.example.domain.utils.Resource
import com.example.data.remote.PokeApi
import com.example.data.utils.NetworkBoundResource
import com.example.domain.entities.PokemonEntity
import com.example.domain.entities.PokemonListEntity
import com.example.domain.repo.IPokemonRepository
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityScoped
class PokemonRepositoryImpl @Inject constructor(
    private val api: PokeApi,
    private val networkBoundResource: NetworkBoundResource
): IPokemonRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): Flow<Resource<PokemonListEntity>> {

        val result = networkBoundResource.triggerCall(api.getPokemonList(limit, offset)) { it ->
            mapFromPokemonListResponse(it)
        }

        return result
    }

    override suspend fun getPokemonInfo(pokemonName: String): Flow<Resource<PokemonEntity>> {

        val result = networkBoundResource.triggerCall(api.getPokemonInfo(pokemonName)) { it ->
            mapFromPokemonResponse(it)
        }

        return result
    }

}
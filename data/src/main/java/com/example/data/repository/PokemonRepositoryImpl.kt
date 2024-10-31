package com.example.data.repository

import com.example.data.mapper.PokemonRepositoryMapper
import com.example.data.mapper.PokemonRepositoryMapper.mapFromPokemonListResponse
import com.example.data.mapper.PokemonRepositoryMapper.mapFromPokemonResponse
import com.example.domain.utils.Resource
import com.example.data.remote.PokeApi
import com.example.domain.entities.PokemonEntity
import com.example.domain.entities.PokemonListEntity
import com.example.domain.repo.IPokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokeApi,
): IPokemonRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): Resource<List<PokemonListEntity>> {
        val response = try {
            mapFromPokemonListResponse(api.getPokemonList(limit, offset))
        } catch (e: Exception){
            return Resource.Error("An Error Occurred")
        }

        return Resource.Success(response)
    }

    override suspend fun getPokemonInfo(pokemonName: String): Resource<PokemonEntity> {
        val response = try {
            mapFromPokemonResponse(api.getPokemonInfo(pokemonName))
        } catch (e: Exception){
            return Resource.Error("An Error Occurred")
        }

        return Resource.Success(response)
    }

}
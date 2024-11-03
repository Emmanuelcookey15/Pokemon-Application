package com.example.data.repository

import android.util.Log
import com.example.data.mapper.PokemonRepositoryMapper.mapFromPokemonListResponse
import com.example.data.mapper.PokemonRepositoryMapper.mapFromPokemonResponse
import com.example.data.mapper.PokemonRepositoryMapper.message
import com.example.data.mapper.PokemonRepositoryMapper.parserErrorBody
import com.example.domain.utils.Resource
import com.example.data.remote.PokeApi
import com.example.domain.entities.PokemonEntity
import com.example.domain.entities.PokemonDataEntity
import com.example.domain.entities.PokemonListEntity
import com.example.domain.repo.IPokemonRepository
import dagger.hilt.android.scopes.ActivityScoped
import timber.log.Timber
import javax.inject.Inject

@ActivityScoped
class PokemonRepositoryImpl @Inject constructor(
    private val api: PokeApi,
): IPokemonRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonListEntity> {

        val result = try {
            val response = api.getPokemonList(limit, offset)
            if (response.isSuccessful){
                response.body()?.let {
                    Resource.Success(data = mapFromPokemonListResponse(it))
                }?: Resource.Error(message = "Unknown error occurred")
            }else{
                Resource.Error(message = parserErrorBody(response.errorBody()))
            }

        } catch (e: Exception){
            return Resource.Error(message = message(e))
        }

        return result
    }

    override suspend fun getPokemonInfo(pokemonName: String): Resource<PokemonEntity> {
        val result = try {
            val response = api.getPokemonInfo(pokemonName)
            if (response.isSuccessful){
                response.body()?.let {
                    Resource.Success(data = mapFromPokemonResponse(it))
                }?: Resource.Error(message = "Unknown error occurred")
            }else{
                Resource.Error(message = parserErrorBody(response.errorBody()))
            }

        } catch (e: Exception){
            Timber.e(e.localizedMessage)
            return Resource.Error(message = message(e))
        }

        return result
    }

}
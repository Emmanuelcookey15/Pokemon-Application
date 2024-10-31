package com.example.data.repository

import com.example.domain.utils.Resource
import com.example.data.remote.PokeApi
import com.example.data.remote.responses.Pokemon
import com.example.data.remote.responses.PokemonList
import com.example.domain.entities.PokemonEntity
import com.example.domain.entities.PokemonListEntity
import com.example.domain.repo.IPokemonRepository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokeApi
): IPokemonRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonListEntity> {
        val response = try {
            api.getPokemonList(limit, offset)
        } catch (e: Exception){
            return Resource.Error("An Error Occurred")
        }

        return Resource.Success(PokemonListEntity())
    }

    override suspend fun getPokemonInfo(pokemonName: String): Resource<PokemonEntity> {
        val response = try {
            api.getPokemonInfo(pokemonName)
        } catch (e: Exception){
            return Resource.Error("An Error Occurred")
        }

        return Resource.Success(PokemonEntity())
    }

}
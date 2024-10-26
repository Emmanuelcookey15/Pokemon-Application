package com.example.pokedexapplication.data.repository

import com.example.pokedexapplication.data.remote.PokeApi
import com.example.pokedexapplication.data.remote.responses.Pokemon
import com.example.pokedexapplication.data.remote.responses.PokemonList
import com.example.pokedexapplication.domain.repo.IPokemonRepository
import com.example.pokedexapplication.common.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepositoryImpl @Inject constructor(
    private val api: PokeApi
): IPokemonRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        val response = try {
            api.getPokemonList(limit, offset)
        } catch (e: Exception){
            return Resource.Error("An Error Occurred")
        }

        return Resource.Success(response)
    }

    override suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        val response = try {
            api.getPokemonInfo(pokemonName)
        } catch (e: Exception){
            return Resource.Error("An Error Occurred")
        }

        return Resource.Success(response)
    }

}
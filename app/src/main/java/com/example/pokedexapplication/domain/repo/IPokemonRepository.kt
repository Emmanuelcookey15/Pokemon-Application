package com.example.pokedexapplication.domain.repo

import com.example.pokedexapplication.data.remote.responses.Pokemon
import com.example.pokedexapplication.data.remote.responses.PokemonList
import com.example.pokedexapplication.reusable_data.utils.Resource
import retrofit2.http.Query

interface IPokemonRepository {

    suspend fun getPokemonList(
       limit: Int,
        offset: Int,
    ): Resource<PokemonList>

    suspend fun getPokemonInfo(
       pokemonName: String
    ): Resource<Pokemon>
}
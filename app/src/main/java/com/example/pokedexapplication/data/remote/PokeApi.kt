package com.example.pokedexapplication.data.remote

import com.example.pokedexapplication.data.remote.responses.Pokemon
import com.example.pokedexapplication.data.remote.responses.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    //https://pokeapi.co/api/v2/

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): PokemonList

    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(
        @Path("name") name: String,
    ): Pokemon

}
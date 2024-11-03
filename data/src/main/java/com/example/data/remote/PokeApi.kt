package com.example.data.remote

import com.example.data.remote.responses.PokemonResponse
import com.example.data.remote.responses.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Response<PokemonListResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(
        @Path("name") name: String,
    ): Response<PokemonResponse>

}
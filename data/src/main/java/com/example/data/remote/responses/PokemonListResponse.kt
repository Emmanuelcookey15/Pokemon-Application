package com.example.data.remote.responses

data class PokemonListResponse(
    val count: Int = 0,
    val next: String? = "",
    val previous: String? = "",
    val results: List<Result>
)
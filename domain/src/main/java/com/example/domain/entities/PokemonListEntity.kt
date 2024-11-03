package com.example.domain.entities

data class PokemonListEntity(
    val count: Int,
    val next: String,
    val pokemonEntities: List<PokemonDataEntity>
)

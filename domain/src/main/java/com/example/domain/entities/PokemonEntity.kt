package com.example.domain.entities

data class PokemonEntity(
    val id: Int = 0,
    val name: String = "",
    val order: Int = 0,
    val is_default: Boolean = false,
    val location_area_encounters: String = "",
    val weight: Int = 0,
    val height: Int = 0,
    val stats: List<StatEntity> = mutableListOf(),
    val types: List<TypeEntity> = mutableListOf(),
)

package com.example.domain.entities

data class PokemonEntity(
    val id: Int = 0,
    val name: String = "",
    val order: Int = 0,
    val isDefault: Boolean = false,
    val locationAreaEncounters: String = "",
    val weight: Int = 0,
    val height: Int = 0,
    val cries: CriesEntity,
    val stats: List<StatEntity> = mutableListOf(),
    val types: List<TypeEntity> = mutableListOf(),
)

package com.example.domain.entities

data class PokemonEntity(
    val id: Int,
    val name: String,
    val order: Int,
    val isDefault: Boolean,
    val locationAreaEncounters: String,
    val weight: Int,
    val height: Int,
    val cries: CriesEntity,
    val stats: List<StatEntity>,
    val types: List<TypeEntity>,
)

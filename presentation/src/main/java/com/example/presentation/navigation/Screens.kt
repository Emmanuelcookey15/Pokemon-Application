package com.example.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object ScreenA

@Serializable
data class ScreenB(
    val pokemonName: String,
    val dominantColor: Int?
)
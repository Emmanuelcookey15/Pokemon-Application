package com.example.presentation.routes

import kotlinx.serialization.Serializable

@Serializable
object ScreenA

@Serializable
data class ScreenB(
    val pokemonName: String,
    val dominantColor: Int?
)
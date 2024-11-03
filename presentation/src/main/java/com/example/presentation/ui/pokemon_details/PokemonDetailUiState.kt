package com.example.presentation.ui.pokemon_details

import com.example.domain.entities.PokemonEntity

sealed interface PokemonDetailUiState {
    data object Loading : PokemonDetailUiState
    data class Success(val data: PokemonEntity?): PokemonDetailUiState
    data class Error(val message:String) : PokemonDetailUiState
}
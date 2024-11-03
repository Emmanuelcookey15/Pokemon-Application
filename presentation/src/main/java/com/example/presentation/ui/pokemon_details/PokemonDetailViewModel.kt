package com.example.presentation.ui.pokemon_details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.PokemonUseCase
import com.example.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonUseCase: PokemonUseCase
): ViewModel() {

    val pokemonDetailUiState = mutableStateOf<PokemonDetailUiState>(PokemonDetailUiState.Loading)



    fun fetchPokemonDetails(pokemonName: String){
        viewModelScope.launch {
            pokemonUseCase.executePokemonDetail(pokemonName)
                .catch { exception -> exception.message  }
                .collect { pokemonInfo ->
                    when(pokemonInfo) {
                        is Resource.Success -> {
                            pokemonDetailUiState.value = PokemonDetailUiState.Success(pokemonInfo.data)
                        }
                        is Resource.Error -> { pokemonDetailUiState.value = PokemonDetailUiState.Error(pokemonInfo.message ?: "Unknown Error")
                        }
                    }
                }
        }
    }

}
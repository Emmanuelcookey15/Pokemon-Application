package com.example.presentation.ui.pokemon_list

import android.content.IntentSender.OnFinished
import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.PokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonUseCase: PokemonUseCase
): ViewModel() {


    fun calcDominantColor(drawable: Drawable, onFinished: () -> Unit) {

    }

}
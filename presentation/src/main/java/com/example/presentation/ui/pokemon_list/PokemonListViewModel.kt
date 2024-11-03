package com.example.presentation.ui.pokemon_list

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import coil3.Image
import coil3.toBitmap
import com.example.common.extentions.Constants.PAGE_SIZE
import com.example.domain.entities.PokemonDataEntity
import com.example.domain.usecase.PokemonUseCase
import com.example.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonUseCase: PokemonUseCase
): ViewModel() {

    private var currentPage = 0

    var pokemonList = mutableStateOf<List<PokemonDataEntity>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    init {
        loadPokemonPaginated()
    }


    fun loadPokemonPaginated() {
        viewModelScope.launch {
            isLoading.value = true
            val result = pokemonUseCase.executePokemonList(PAGE_SIZE, currentPage * PAGE_SIZE)
            when(result) {
                is Resource.Success -> {
                    result.data?.let { data ->
                        endReached.value = currentPage * PAGE_SIZE >= data.count
                        currentPage++

                        loadError.value = ""
                        isLoading.value = false
                        pokemonList.value += data.pokemonEntities
                    }

                }
                is Resource.Error -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }
                is Resource.Loading -> TODO()
            }
        }
    }

    fun calcDominantColor(image: Image, onFinished: (Color) -> Unit) {
        val imageBitmap = image.toBitmap(image.width, image.height)

        val bmp = imageBitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinished(Color(colorValue))

            }

        }
    }

}
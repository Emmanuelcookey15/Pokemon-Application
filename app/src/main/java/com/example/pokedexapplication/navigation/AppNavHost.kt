package com.example.pokedexapplication.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.pokedexapplication.presentation.pokemon_list.PokemonListScreen
import kotlinx.serialization.Serializable

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Any,
){
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ){
        composable<ScreenA> {
            PokemonListScreen(navController = navController)
        }
        composable<ScreenB> {
            val args = it.toRoute<ScreenB>()
            val color = args.dominantColor?.let { color -> Color(color) } ?:  Color.White
            val pokemonName = args.pokemonName
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "${pokemonName}, ${color} years old")
            }

        }
    }
}

@Serializable
object ScreenA

@Serializable
data class ScreenB(
    val pokemonName: String,
    val dominantColor: Long?
)
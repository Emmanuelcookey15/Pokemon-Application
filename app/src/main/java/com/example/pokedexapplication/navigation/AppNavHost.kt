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
import com.example.presentation.navigation.ScreenA
import com.example.presentation.navigation.ScreenB
import com.example.presentation.ui.pokemon_details.PokemonDetailScreen
import com.example.presentation.ui.pokemon_list.PokemonListScreen
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
            PokemonDetailScreen(
                color,
                pokemonName,
                navController
            )


        }
    }
}
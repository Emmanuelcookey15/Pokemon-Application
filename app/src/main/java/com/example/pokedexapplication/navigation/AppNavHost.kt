package com.example.pokedexapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.presentation.routes.ScreenA
import com.example.presentation.routes.ScreenB
import com.example.presentation.ui.pokemon_details.PokemonDetailScreen
import com.example.presentation.ui.pokemon_list.PokemonListScreen

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
            PokemonListScreen(
                onNavigateClick = { str, int ->
                    navController.navigate(
                        ScreenB(str, int)
                    )
                }
            )
        }
        composable<ScreenB> {
            val args = it.toRoute<ScreenB>()
            val color = args.dominantColor?.let { color -> Color(color) } ?:  Color.White
            val pokemonName = args.pokemonName
            PokemonDetailScreen(
                color,
                pokemonName,
                onBackPressed = {
                    navController.popBackStack()
                }
            )


        }
    }
}
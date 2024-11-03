package com.example.pokedexapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.pokedexapplication.navigation.AppNavHost
import com.example.pokedexapplication.ui.theme.PokedexApplicationTheme
import com.example.presentation.navigation.ScreenA
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokedexApplicationTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController, startDestination = ScreenA)
            }
        }
    }
}

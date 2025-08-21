package com.vadymmy.ricktionary.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vadymmy.ricktionary.ui.characters.CharactersScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppNavRoute.Characters
    ) {
        composable<AppNavRoute.Characters> {
            CharactersScreen()
        }
    }
}

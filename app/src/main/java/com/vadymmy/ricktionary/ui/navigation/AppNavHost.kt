package com.vadymmy.ricktionary.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vadymmy.ricktionary.ui.characters.CharactersScreen
import com.vadymmy.ricktionary.ui.navigation.model.AppNavRoute
import com.vadymmy.ricktionary.ui.navigation.model.NavigationEffect
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AppNavHost(appNavigator: AppNavigator) {
    val navController = rememberNavController()

    AppNavigationEffectHandler(appNavigator = appNavigator, navController = navController)
    
    NavHost(
        navController = navController,
        startDestination = AppNavRoute.Characters
    ) {
        composable<AppNavRoute.Characters> {
            CharactersScreen()
        }
    }
}

@Composable
private fun AppNavigationEffectHandler(
    appNavigator: AppNavigator,
    navController: NavController
) {
    LaunchedEffect(appNavigator) {
        appNavigator.navigationEffect.collectLatest { effect ->
            when(effect) {
                NavigationEffect.Back -> navController.popBackStack()
                is NavigationEffect.Navigate -> navController.navigate(effect.route)
            }
        }
    }
}

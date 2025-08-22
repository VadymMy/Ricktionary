package com.vadymmy.ricktionary.ui.navigation.model

sealed interface NavigationEffect {
    data class Navigate(val route: AppNavRoute) : NavigationEffect
    data object Back : NavigationEffect
}

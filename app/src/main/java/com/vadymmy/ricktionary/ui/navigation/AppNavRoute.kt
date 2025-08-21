package com.vadymmy.ricktionary.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppNavRoute {
    @Serializable
    data object Characters : AppNavRoute
}

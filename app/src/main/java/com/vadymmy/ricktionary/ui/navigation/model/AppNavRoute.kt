package com.vadymmy.ricktionary.ui.navigation.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppNavRoute {
    @Serializable
    data object Characters : AppNavRoute

    @Serializable
    data class CharacterDetails(val id: Int) : AppNavRoute
}

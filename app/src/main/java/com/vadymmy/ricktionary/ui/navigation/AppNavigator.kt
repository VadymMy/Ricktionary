package com.vadymmy.ricktionary.ui.navigation

import com.vadymmy.ricktionary.ui.navigation.model.AppNavRoute
import com.vadymmy.ricktionary.ui.navigation.model.NavigationEffect
import kotlinx.coroutines.flow.SharedFlow

interface AppNavigator {
    val navigationEffect: SharedFlow<NavigationEffect>
    fun navigateTo(route: AppNavRoute)
    fun back()
}

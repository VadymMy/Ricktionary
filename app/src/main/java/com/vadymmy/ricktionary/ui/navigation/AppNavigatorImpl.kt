package com.vadymmy.ricktionary.ui.navigation

import com.vadymmy.ricktionary.ui.navigation.model.AppNavRoute
import com.vadymmy.ricktionary.ui.navigation.model.NavigationEffect
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

class AppNavigatorImpl @Inject constructor() : AppNavigator {
    override val navigationEffect = MutableSharedFlow<NavigationEffect>()

    override suspend fun navigateTo(route: AppNavRoute) {
        navigationEffect.emit(NavigationEffect.Navigate(route))
    }

    override suspend fun back() {
        navigationEffect.emit(NavigationEffect.Back)
    }
}

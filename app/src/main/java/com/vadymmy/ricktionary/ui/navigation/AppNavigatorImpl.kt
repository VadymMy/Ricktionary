package com.vadymmy.ricktionary.ui.navigation

import com.vadymmy.ricktionary.ui.navigation.model.AppNavRoute
import com.vadymmy.ricktionary.ui.navigation.model.NavigationEffect
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

class AppNavigatorImpl @Inject constructor() : AppNavigator {
    override val navigationEffect = MutableSharedFlow<NavigationEffect>()

    override fun navigateTo(route: AppNavRoute) {
        navigationEffect.tryEmit(NavigationEffect.Navigate(route))
    }

    override fun back() {
        navigationEffect.tryEmit(NavigationEffect.Back)
    }
}

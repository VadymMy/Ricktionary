package com.vadymmy.ricktionary.di.navigation

import com.vadymmy.ricktionary.ui.navigation.AppNavigator
import com.vadymmy.ricktionary.ui.navigation.AppNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {

    @Binds
    @Singleton
    abstract fun bindAppNavigator(navigator: AppNavigatorImpl): AppNavigator
}

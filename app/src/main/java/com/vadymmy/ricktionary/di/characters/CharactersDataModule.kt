package com.vadymmy.ricktionary.di.characters

import com.vadymmy.ricktionary.data.characters.CharactersRepositoryImpl
import com.vadymmy.ricktionary.data.characters.remote.source.CharactersRemoteDataSource
import com.vadymmy.ricktionary.data.characters.remote.source.CharactersRemoteDataSourceImpl
import com.vadymmy.ricktionary.domain.characters.CharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CharactersDataModule {
    @Binds
    @Singleton
    abstract fun bindCharactersRemoteDataSource(remoteDataSourceImpl: CharactersRemoteDataSourceImpl): CharactersRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindCharactersRepository(repositoryImpl: CharactersRepositoryImpl): CharactersRepository
}

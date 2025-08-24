package com.vadymmy.ricktionary.di.characters

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.vadymmy.ricktionary.data.characters.CharactersRepositoryImpl
import com.vadymmy.ricktionary.data.characters.local.dao.CharactersDao
import com.vadymmy.ricktionary.data.characters.local.db.CharactersDatabase
import com.vadymmy.ricktionary.data.characters.local.model.CharacterWithRelationsEntity
import com.vadymmy.ricktionary.data.characters.local.source.CharactersLocalDataSource
import com.vadymmy.ricktionary.data.characters.local.source.CharactersLocalDataSourceImpl
import com.vadymmy.ricktionary.data.characters.paging.CharactersRemoteMediator
import com.vadymmy.ricktionary.data.characters.remote.source.CharactersRemoteDataSource
import com.vadymmy.ricktionary.data.characters.remote.source.CharactersRemoteDataSourceImpl
import com.vadymmy.ricktionary.domain.characters.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val CHARACTERS_DB_NAME = "characters.db"
private const val CHARACTERS_PAGE_SIZE = 20

@Module
@InstallIn(SingletonComponent::class)
class CharactersDataModule {
    @Provides
    @Singleton
    fun provideCharactersDatabase(@ApplicationContext context: Context): CharactersDatabase {
        return Room.databaseBuilder(context, CharactersDatabase::class.java, CHARACTERS_DB_NAME).build()
    }

    @Provides
    @Singleton
    fun provideCharactersDao(charactersDatabase: CharactersDatabase): CharactersDao = charactersDatabase.charactersDao()

    @Provides
    @Singleton
    fun provideCharactersLocalDataSource(localDataSourceImpl: CharactersLocalDataSourceImpl): CharactersLocalDataSource {
        return localDataSourceImpl
    }

    @Provides
    @Singleton
    fun provideCharactersRemoteDataSource(remoteDataSourceImpl: CharactersRemoteDataSourceImpl): CharactersRemoteDataSource {
        return remoteDataSourceImpl
    }

    @Provides
    @OptIn(ExperimentalPagingApi::class)
    fun provideCharactersPager(
        charactersRemoteMediator: CharactersRemoteMediator,
        charactersLocalDataSource: CharactersLocalDataSource
    ) : Pager<Int, CharacterWithRelationsEntity> {
        return Pager(
            config = PagingConfig(pageSize = CHARACTERS_PAGE_SIZE),
            remoteMediator = charactersRemoteMediator,
            pagingSourceFactory = charactersLocalDataSource::getCharactersPagingSource
        )
    }

    @Provides
    @Singleton
    fun provideCharactersRepository(repositoryImpl: CharactersRepositoryImpl): CharactersRepository = repositoryImpl
}

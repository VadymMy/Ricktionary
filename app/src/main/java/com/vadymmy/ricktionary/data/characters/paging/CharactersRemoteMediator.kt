package com.vadymmy.ricktionary.data.characters.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.vadymmy.ricktionary.data.characters.local.db.CharactersTransactionProvider
import com.vadymmy.ricktionary.data.characters.local.model.CharacterPageRemoteKeysEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterWithRelationsEntity
import com.vadymmy.ricktionary.data.characters.local.source.CharactersLocalDataSource
import com.vadymmy.ricktionary.data.characters.remote.mapper.toEntityModel
import com.vadymmy.ricktionary.data.characters.remote.mapper.toEpisodeEntities
import com.vadymmy.ricktionary.data.characters.remote.model.CharacterDto
import com.vadymmy.ricktionary.data.characters.remote.source.CharactersRemoteDataSource
import javax.inject.Inject

private const val FIRST_PAGE = 1
private const val PAGE_URL_DELIMITER = "page="

@OptIn(ExperimentalPagingApi::class)
class CharactersRemoteMediator @Inject constructor(
    private val remoteDataSource: CharactersRemoteDataSource,
    private val localDataSource: CharactersLocalDataSource,
    private val charactersTransactionProvider: CharactersTransactionProvider
) : RemoteMediator<Int, CharacterWithRelationsEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterWithRelationsEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> FIRST_PAGE

            LoadType.PREPEND -> {
                val firstPage = localDataSource.getFirstCharacterRemoteKeysPage()
                val remoteKey = firstPage?.let { localDataSource.getCharacterPageRemoteKeys(it) }
                val previousPage = remoteKey?.previousPage ?: return MediatorResult.Success(endOfPaginationReached = true)

                previousPage
            }

            LoadType.APPEND -> {
                val lastPage = localDataSource.getLastCharacterRemoteKeysPage()
                val remoteKey = lastPage?.let { localDataSource.getCharacterPageRemoteKeys(it) }
                val nextPage = remoteKey?.nextPage ?: return MediatorResult.Success(endOfPaginationReached = true)

                nextPage
            }
        }

        return try {
            val response = remoteDataSource.getCharacters(page)
            val characterPageRemoteKeys = CharacterPageRemoteKeysEntity(
                page = page,
                previousPage = response.info.previousPageUrl.extractPageNumber(),
                nextPage = response.info.nextPageUrl.extractPageNumber()
            )

            charactersTransactionProvider.runAsTransaction {
                localDataSource.insertCharacterPageRemoteKeys(characterPageRemoteKeys)

                response.characters.forEach { characterDto ->
                    saveCharacterToDB(character = characterDto)
                }
            }

            MediatorResult.Success(endOfPaginationReached = characterPageRemoteKeys.nextPage == null)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun saveCharacterToDB(character: CharacterDto) {
        val characterEntity = character.toEntityModel()
        val characterOrigin = character.origin.toEntityModel()
        val characterLocation = character.location.toEntityModel()
        val characterEpisodes = character.toEpisodeEntities()

        localDataSource.insertCharacter(
            character = characterEntity,
            origin = characterOrigin,
            location = characterLocation,
            episodes = characterEpisodes
        )
    }

    private fun String?.extractPageNumber(): Int? = this?.substringAfter(PAGE_URL_DELIMITER)?.toIntOrNull()
}

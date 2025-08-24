package com.vadymmy.ricktionary.data.characters.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.vadymmy.ricktionary.data.characters.local.db.CharactersTransactionProvider
import com.vadymmy.ricktionary.data.characters.local.model.CharacterWithRelationsEntity
import com.vadymmy.ricktionary.data.characters.local.source.CharactersLocalDataSource
import com.vadymmy.ricktionary.data.characters.remote.mapper.toEntityModel
import com.vadymmy.ricktionary.data.characters.remote.mapper.toEpisodeEntities
import com.vadymmy.ricktionary.data.characters.remote.model.CharacterDto
import com.vadymmy.ricktionary.data.characters.remote.source.CharactersRemoteDataSource
import javax.inject.Inject

private const val FIRST_PAGE = 1

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
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val loadedPagesNumber = state.pages.sumOf { it.data.size }
                val currentPage = (loadedPagesNumber / state.config.pageSize).coerceAtLeast(FIRST_PAGE)

                currentPage + 1
            }
        }

        return try {
            val response = remoteDataSource.getCharacters(page)
            val isLastPage = response.info.nextPageUrl == null

            charactersTransactionProvider.runAsTransaction {
                if (loadType == LoadType.REFRESH) localDataSource.clearCharacters()

                response.characters.forEach { characterDto ->
                    saveCharacterToDB(character = characterDto)
                }
            }

            MediatorResult.Success(endOfPaginationReached = isLastPage)
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
}

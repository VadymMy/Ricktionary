package com.vadymmy.ricktionary.data.characters

import androidx.paging.Pager
import com.vadymmy.ricktionary.data.characters.local.mapper.toDomainModel
import com.vadymmy.ricktionary.data.characters.local.mapper.toDomainModels
import com.vadymmy.ricktionary.data.characters.local.model.CharacterWithRelationsEntity
import com.vadymmy.ricktionary.data.characters.local.source.CharactersLocalDataSource
import com.vadymmy.ricktionary.data.characters.remote.mapper.toDomainModel
import com.vadymmy.ricktionary.data.characters.remote.source.CharactersRemoteDataSource
import com.vadymmy.ricktionary.domain.characters.CharactersRepository
import com.vadymmy.ricktionary.domain.characters.model.Character
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersLocalDataSource: CharactersLocalDataSource,
    private val charactersRemoteDataSource: CharactersRemoteDataSource,
    charactersPager: Pager<Int, CharacterWithRelationsEntity>
) : CharactersRepository {

    override val charactersPagingDataFlow = charactersPager.flow.map { pagingData ->
        pagingData.toDomainModels()
    }

    override suspend fun areCharactersSaved() = charactersLocalDataSource.areCharactersSaved()

    override suspend fun getCharacter(id: Int): Character {
        val characterFromCache = charactersLocalDataSource.getCharacterById(id = id)?.toDomainModel()
        return characterFromCache ?: charactersRemoteDataSource.getCharacter(id = id).toDomainModel()
    }
}

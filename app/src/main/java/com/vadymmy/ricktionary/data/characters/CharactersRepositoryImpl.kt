package com.vadymmy.ricktionary.data.characters

import com.vadymmy.ricktionary.data.characters.local.mapper.toDomainModel
import com.vadymmy.ricktionary.data.characters.local.mapper.toDomainModels
import com.vadymmy.ricktionary.data.characters.local.source.CharactersLocalDataSource
import com.vadymmy.ricktionary.data.characters.remote.mapper.toDomainModel
import com.vadymmy.ricktionary.data.characters.remote.mapper.toEntityModel
import com.vadymmy.ricktionary.data.characters.remote.mapper.toEpisodeEntities
import com.vadymmy.ricktionary.data.characters.remote.model.CharacterDto
import com.vadymmy.ricktionary.data.characters.remote.source.CharactersRemoteDataSource
import com.vadymmy.ricktionary.domain.characters.CharactersRepository
import com.vadymmy.ricktionary.domain.characters.model.Character
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersLocalDataSource: CharactersLocalDataSource,
    private val charactersRemoteDataSource: CharactersRemoteDataSource
) : CharactersRepository {
    override val charactersFlow = charactersLocalDataSource.charactersFlow.map {
        it.toDomainModels()
    }

    override suspend fun areCharactersSaved() = charactersLocalDataSource.areCharactersSaved()

    override suspend fun fetchCharacters() {
        val response = charactersRemoteDataSource.getCharacters()

        response.characters.forEach {
            saveCharacterToDB(it)
        }
    }

    override suspend fun getCharacter(id: Int): Character {
        val characterFromCache = charactersLocalDataSource.getCharacterById(id = id)?.toDomainModel()
        return characterFromCache ?: charactersRemoteDataSource.getCharacter(id = id).toDomainModel()
    }

    private suspend fun saveCharacterToDB(character: CharacterDto) {
        val characterEntity = character.toEntityModel()
        val characterOrigin = character.origin.toEntityModel()
        val characterLocation = character.location.toEntityModel()
        val characterEpisodes = character.toEpisodeEntities()

        charactersLocalDataSource.insertCharacter(
            character = characterEntity,
            origin = characterOrigin,
            location = characterLocation,
            episodes = characterEpisodes
        )
    }
}

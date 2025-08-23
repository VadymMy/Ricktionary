package com.vadymmy.ricktionary.data.characters

import com.vadymmy.ricktionary.data.characters.remote.mapper.toDomainModel
import com.vadymmy.ricktionary.data.characters.remote.mapper.toDomainModels
import com.vadymmy.ricktionary.data.characters.remote.source.CharactersRemoteDataSource
import com.vadymmy.ricktionary.domain.characters.CharactersRepository
import com.vadymmy.ricktionary.domain.characters.model.Character
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersRemoteDataSource: CharactersRemoteDataSource
) : CharactersRepository {
    override val charactersFlow = MutableStateFlow(emptyList<Character>())

    override suspend fun fetchCharacters() {
        val response = charactersRemoteDataSource.getCharacters()
        val charactersList = response.characters.toDomainModels()

        charactersFlow.emit(charactersList)
    }

    override suspend fun getCharacter(id: Int): Character {
        val characterFromCache = charactersFlow.value.find { it.id == id }
        return characterFromCache ?: charactersRemoteDataSource.getCharacter(id = id).toDomainModel()
    }
}

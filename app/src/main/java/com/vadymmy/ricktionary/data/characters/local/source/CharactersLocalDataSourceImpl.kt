package com.vadymmy.ricktionary.data.characters.local.source

import com.vadymmy.ricktionary.data.characters.local.dao.CharactersDao
import com.vadymmy.ricktionary.data.characters.local.dao.CharactersRemoteKeysDao
import com.vadymmy.ricktionary.data.characters.local.model.CharacterEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterEpisodeEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterLocationEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterOriginEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterInEpisodeEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterPageRemoteKeysEntity
import javax.inject.Inject

class CharactersLocalDataSourceImpl @Inject constructor(
    private val charactersDao: CharactersDao,
    private val charactersRemoteKeysDao: CharactersRemoteKeysDao
) : CharactersLocalDataSource {
    override fun getCharactersPagingSource() = charactersDao.getCharactersPagingSource()

    override suspend fun areCharactersSaved() = charactersDao.getSavedCharactersNumber() > 0

    override suspend fun getCharacterById(id: Int) = charactersDao.getCharacterById(characterId = id)

    override suspend fun getCharacterPageRemoteKeys(page: Int) = charactersRemoteKeysDao.getRemoteKeysByPage(page)

    override suspend fun getFirstCharacterRemoteKeysPage() = charactersRemoteKeysDao.getFirstRemoteKeysPage()

    override suspend fun getLastCharacterRemoteKeysPage() = charactersRemoteKeysDao.getLastRemoteKeysPage()

    override suspend fun insertCharacter(
        character: CharacterEntity,
        origin: CharacterOriginEntity?,
        location: CharacterLocationEntity?,
        episodes: List<CharacterEpisodeEntity>
    ) {
        val characterInEpisodes = episodes.map { episode ->
            CharacterInEpisodeEntity(characterId = character.id, episodeId = episode.id)
        }

        charactersDao.insertCharacterWithRelations(
            character = character,
            origin = origin,
            location = location,
            episodes = episodes,
            characterInEpisodes = characterInEpisodes
        )
    }

    override suspend fun insertCharacterPageRemoteKeys(characterPageRemoteKeysEntity: CharacterPageRemoteKeysEntity) {
        charactersRemoteKeysDao.insertRemoteKeys(remoteKeys = characterPageRemoteKeysEntity)
    }

    override suspend fun clearCharacters() = charactersDao.clearCharacters()

    override suspend fun clearCharactersPageRemoteKeys() = charactersRemoteKeysDao.clearRemoteKeys()
}

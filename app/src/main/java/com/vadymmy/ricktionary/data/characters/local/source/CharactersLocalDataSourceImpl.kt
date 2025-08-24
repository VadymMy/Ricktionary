package com.vadymmy.ricktionary.data.characters.local.source

import com.vadymmy.ricktionary.data.characters.local.dao.CharactersDao
import com.vadymmy.ricktionary.data.characters.local.model.CharacterEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterEpisodeEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterLocationEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterOriginEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterInEpisodeEntity
import javax.inject.Inject

class CharactersLocalDataSourceImpl @Inject constructor(
    private val charactersDao: CharactersDao
) : CharactersLocalDataSource {
    override fun getCharactersPagingSource() = charactersDao.getCharactersPagingSource()

    override suspend fun areCharactersSaved() = charactersDao.getSavedCharactersNumber() > 0

    override suspend fun getCharacterById(id: Int) = charactersDao.getCharacterById(characterId = id)

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

    override suspend fun clearCharacters() = charactersDao.clearCharacters()
}

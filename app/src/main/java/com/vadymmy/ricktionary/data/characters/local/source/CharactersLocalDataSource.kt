package com.vadymmy.ricktionary.data.characters.local.source

import androidx.paging.PagingSource
import com.vadymmy.ricktionary.data.characters.local.model.CharacterEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterEpisodeEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterLocationEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterOriginEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterPageRemoteKeysEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterWithRelationsEntity

interface CharactersLocalDataSource {
    fun getCharactersPagingSource(): PagingSource<Int, CharacterWithRelationsEntity>

    suspend fun areCharactersSaved(): Boolean

    suspend fun getCharacterById(id: Int): CharacterWithRelationsEntity?

    suspend fun getCharacterPageRemoteKeys(page: Int): CharacterPageRemoteKeysEntity?

    suspend fun getFirstCharacterRemoteKeysPage(): Int?

    suspend fun getLastCharacterRemoteKeysPage(): Int?

    suspend fun insertCharacter(
        character: CharacterEntity,
        origin: CharacterOriginEntity?,
        location: CharacterLocationEntity?,
        episodes: List<CharacterEpisodeEntity>
    )

    suspend fun insertCharacterPageRemoteKeys(characterPageRemoteKeysEntity: CharacterPageRemoteKeysEntity)

    suspend fun clearCharacters()

    suspend fun clearCharactersPageRemoteKeys()
}

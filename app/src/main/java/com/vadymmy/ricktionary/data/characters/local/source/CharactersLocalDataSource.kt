package com.vadymmy.ricktionary.data.characters.local.source

import com.vadymmy.ricktionary.data.characters.local.model.CharacterEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterEpisodeEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterLocationEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterOriginEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterWithRelationsEntity
import kotlinx.coroutines.flow.Flow

interface CharactersLocalDataSource {
    val charactersFlow: Flow<List<CharacterWithRelationsEntity>>

    suspend fun getCharacterById(id: Int): CharacterWithRelationsEntity?

    suspend fun insertCharacter(
        character: CharacterEntity,
        origin: CharacterOriginEntity?,
        location: CharacterLocationEntity?,
        episodes: List<CharacterEpisodeEntity>
    )
}

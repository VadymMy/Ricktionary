package com.vadymmy.ricktionary.data.characters.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.vadymmy.ricktionary.data.characters.local.model.CharacterEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterEpisodeEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterInEpisodeEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterLocationEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterOriginEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterWithRelationsEntity

@Dao
interface CharactersDao {
    @Transaction
    @Query("SELECT * FROM ${CharacterEntity.TABLE_NAME}")
    fun getCharactersPagingSource(): PagingSource<Int, CharacterWithRelationsEntity>

    @Transaction
    @Query("SELECT COUNT(*) FROM ${CharacterEntity.TABLE_NAME}")
    suspend fun getSavedCharactersNumber(): Int

    @Transaction
    @Query("SELECT * FROM ${CharacterEntity.TABLE_NAME} WHERE id = :characterId")
    suspend fun getCharacterById(characterId: Int): CharacterWithRelationsEntity?

    @Transaction
    suspend fun insertCharacterWithRelations(
        character: CharacterEntity,
        origin: CharacterOriginEntity?,
        location: CharacterLocationEntity?,
        episodes: List<CharacterEpisodeEntity>,
        characterInEpisodes: List<CharacterInEpisodeEntity>
    ) {
        origin?.let { insertOrigin(it) }
        location?.let { insertLocation(it) }
        insertCharacter(character)
        insertEpisodes(episodes)
        insertCharacterInEpisodes(characterInEpisodes)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: CharacterEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrigin(origin: CharacterOriginEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLocation(location: CharacterLocationEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEpisodes(episodes: List<CharacterEpisodeEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacterInEpisodes(characterInEpisodes: List<CharacterInEpisodeEntity>)

    @Query("DELETE FROM ${CharacterEntity.TABLE_NAME}")
    suspend fun clearCharacters()
}

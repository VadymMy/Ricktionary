package com.vadymmy.ricktionary.data.characters.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vadymmy.ricktionary.data.characters.local.dao.CharactersDao
import com.vadymmy.ricktionary.data.characters.local.model.CharacterEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterEpisodeEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterInEpisodeEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterLocationEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterOriginEntity

@Database(
    entities = [
        CharacterEntity::class,
        CharacterOriginEntity::class,
        CharacterLocationEntity::class,
        CharacterEpisodeEntity::class,
        CharacterInEpisodeEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CharactersDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
}

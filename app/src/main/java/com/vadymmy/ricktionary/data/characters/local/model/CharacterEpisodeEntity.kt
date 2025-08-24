package com.vadymmy.ricktionary.data.characters.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = CharacterEpisodeEntity.TABLE_NAME)
data class CharacterEpisodeEntity(
    @PrimaryKey val id: Int = 0,
    val url: String
) {
    companion object {
        const val TABLE_NAME = "episodes"
    }
}

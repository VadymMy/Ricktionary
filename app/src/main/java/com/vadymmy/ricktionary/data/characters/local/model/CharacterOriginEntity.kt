package com.vadymmy.ricktionary.data.characters.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = CharacterOriginEntity.TABLE_NAME)
data class CharacterOriginEntity(
    @PrimaryKey val id: Int = 0,
    val name: String,
    val url: String
) {
    companion object {
        const val TABLE_NAME = "origins"
    }
}

package com.vadymmy.ricktionary.data.characters.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = CharacterPageRemoteKeysEntity.TABLE_NAME)
data class CharacterPageRemoteKeysEntity(
    @PrimaryKey val page: Int,
    val previousPage: Int?,
    val nextPage: Int?
) {
    companion object {
        const val TABLE_NAME = "character_remote_keys"
    }
}

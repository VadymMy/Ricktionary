package com.vadymmy.ricktionary.data.characters.local.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = CharacterInEpisodeEntity.TABLE_NAME,
    primaryKeys = ["characterId", "episodeId"],
    foreignKeys = [
        ForeignKey(
            entity = CharacterEntity::class,
            parentColumns = ["id"],
            childColumns = ["characterId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CharacterEpisodeEntity::class,
            parentColumns = ["id"],
            childColumns = ["episodeId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CharacterInEpisodeEntity(
    val characterId: Int,
    val episodeId: Int
) {
    companion object {
        const val TABLE_NAME = "characters_in_episodes"
    }
}

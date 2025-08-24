package com.vadymmy.ricktionary.data.characters.local.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CharacterWithRelationsEntity(
    @Embedded val character: CharacterEntity,

    @Relation(
        entityColumn = "id",
        parentColumn = "originId"
    )
    val origin: CharacterOriginEntity?,

    @Relation(
        entityColumn = "id",
        parentColumn = "locationId"
    )
    val location: CharacterLocationEntity?,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = CharacterInEpisodeEntity::class,
            parentColumn = "characterId",
            entityColumn = "episodeId"
        )
    )
    val episodes: List<CharacterEpisodeEntity> = emptyList()
)
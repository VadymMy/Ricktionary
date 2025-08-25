package com.vadymmy.ricktionary.data.characters.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = CharacterEntity.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = CharacterLocationEntity::class,
            parentColumns = ["id"],
            childColumns = ["locationId"]
        ),
        ForeignKey(
            entity = CharacterOriginEntity::class,
            parentColumns = ["id"],
            childColumns = ["originId"]
        )
    ],
    indices = [Index("locationId"), Index("originId")]
)
data class CharacterEntity(
    @PrimaryKey val id: Int,
    val locationId: Int?,
    val originId: Int?,
    val url: String,
    val name: String,
    val type: String,
    val species: String,
    val imageUrl: String,
    val statusKey: Int,
    val genderKey: Int
) {
    companion object {
        const val TABLE_NAME = "characters"
    }
}
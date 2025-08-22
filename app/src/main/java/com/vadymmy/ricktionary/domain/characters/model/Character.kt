package com.vadymmy.ricktionary.domain.characters.model

data class Character(
    val id: Int,
    val name: String,
    val url: String,
    val status: CharacterStatus,
    val species: String,
    val type: String,
    val gender: CharacterGender,
    val origin: CharacterOrigin,
    val location: CharacterLocation,
    val imageUrl: String,
    val episodes: List<String>
)

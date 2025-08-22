package com.vadymmy.ricktionary.ui.characters.list.model

data class CharacterItemUiModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val location: String,
    val status: CharacterStatusUiModel
)

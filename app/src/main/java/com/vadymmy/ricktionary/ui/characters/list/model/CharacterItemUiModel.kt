package com.vadymmy.ricktionary.ui.characters.list.model

import com.vadymmy.ricktionary.ui.characters.common.model.CharacterGenderUiModel
import com.vadymmy.ricktionary.ui.characters.common.model.CharacterStatusUiModel

data class CharacterItemUiModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val location: String,
    val status: CharacterStatusUiModel,
    val gender: CharacterGenderUiModel
)

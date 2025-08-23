package com.vadymmy.ricktionary.ui.characters.details.model

import com.vadymmy.ricktionary.ui.characters.common.model.CharacterGenderUiModel
import com.vadymmy.ricktionary.ui.characters.common.model.CharacterStatusUiModel

data class CharacterUiModel(
    val id: Int,
    val name: String,
    val type: String,
    val species: String,
    val imageUrl: String,
    val origin: String,
    val location: String,
    val episodesNumber: Int,
    val status: CharacterStatusUiModel,
    val gender: CharacterGenderUiModel
)

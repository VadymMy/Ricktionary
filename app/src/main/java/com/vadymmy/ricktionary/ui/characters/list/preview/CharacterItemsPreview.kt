package com.vadymmy.ricktionary.ui.characters.list.preview

import com.vadymmy.ricktionary.ui.characters.common.model.CharacterGenderUiModel
import com.vadymmy.ricktionary.ui.characters.list.model.CharacterItemUiModel
import com.vadymmy.ricktionary.ui.characters.common.model.CharacterStatusUiModel

object CharacterItemsPreview {
    val characterItems = listOf(
        CharacterItemUiModel(
            id = 0,
            name = "Rick Sanchez",
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            location = "Citadel of Ricks",
            status = CharacterStatusUiModel.Alive,
            gender = CharacterGenderUiModel.Male
        ),
        CharacterItemUiModel(
            id = 1,
            name = "Morty Smith",
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
            location = "Citadel of Ricks",
            status = CharacterStatusUiModel.Alive,
            gender = CharacterGenderUiModel.Male
        ),
        CharacterItemUiModel(
            id = 2,
            name = "Adjudicator Rick",
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/8.jpeg",
            location = "Citadel of Ricks",
            status = CharacterStatusUiModel.Dead,
            gender = CharacterGenderUiModel.Male
        ),
        CharacterItemUiModel(
            id = 3,
            name = "Alien Morty",
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/14.jpeg",
            location = "Citadel of Ricks",
            status = CharacterStatusUiModel.Unknown,
            gender = CharacterGenderUiModel.Unknown
        )
    )
}

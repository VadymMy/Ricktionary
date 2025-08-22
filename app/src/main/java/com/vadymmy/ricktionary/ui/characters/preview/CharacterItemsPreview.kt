package com.vadymmy.ricktionary.ui.characters.preview

import com.vadymmy.ricktionary.ui.characters.model.CharacterItemUiModel
import com.vadymmy.ricktionary.ui.characters.model.CharacterStatusUiModel

object CharacterItemsPreview {
    val characterItems = listOf(
        CharacterItemUiModel(
            id = 0,
            name = "Rick Sanchez",
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            location = "Citadel of Ricks",
            status = CharacterStatusUiModel.Alive
        ),
        CharacterItemUiModel(
            id = 1,
            name = "Morty Smith",
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
            location = "Citadel of Ricks",
            status = CharacterStatusUiModel.Alive
        ),
        CharacterItemUiModel(
            id = 2,
            name = "Adjudicator Rick",
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/8.jpeg",
            location = "Citadel of Ricks",
            status = CharacterStatusUiModel.Dead
        ),
        CharacterItemUiModel(
            id = 3,
            name = "Alien Morty",
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/14.jpeg",
            location = "Citadel of Ricks",
            status = CharacterStatusUiModel.Unknown
        )
    )
}

package com.vadymmy.ricktionary.ui.characters.common.preview

import com.vadymmy.ricktionary.ui.characters.common.model.CharacterGenderUiModel
import com.vadymmy.ricktionary.ui.characters.list.model.CharacterItemUiModel
import com.vadymmy.ricktionary.ui.characters.common.model.CharacterStatusUiModel
import com.vadymmy.ricktionary.ui.characters.details.model.CharacterUiModel

object CharacterPreview {
    val character = CharacterUiModel(
        id = 0,
        name = "Rick Sanchez",
        type = "",
        species = "Human",
        imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        origin = "Earth",
        location = "Citadel of Ricks",
        episodesNumber = 51,
        status = CharacterStatusUiModel.Alive,
        gender = CharacterGenderUiModel.Male,
    )

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

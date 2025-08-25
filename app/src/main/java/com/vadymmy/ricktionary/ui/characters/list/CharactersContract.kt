package com.vadymmy.ricktionary.ui.characters.list

import com.vadymmy.ricktionary.ui.characters.list.model.CharacterItemUiModel

class CharactersUiState

sealed interface CharactersIntent {
    data class CharacterItemClicked(val character: CharacterItemUiModel) : CharactersIntent
}

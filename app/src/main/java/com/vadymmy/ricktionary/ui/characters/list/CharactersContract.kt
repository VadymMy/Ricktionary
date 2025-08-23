package com.vadymmy.ricktionary.ui.characters.list

import com.vadymmy.ricktionary.ui.characters.list.model.CharacterItemUiModel

data class CharactersUiState(
    val isLoading: Boolean = true,
    val showLoadingError: Boolean = false,
    val characters: List<CharacterItemUiModel> = emptyList()
)

sealed interface CharactersIntent {
    data class CharacterItemClicked(val character: CharacterItemUiModel) : CharactersIntent
}

package com.vadymmy.ricktionary.ui.characters

import com.vadymmy.ricktionary.ui.characters.model.CharacterItemUiModel

data class CharactersUiState(
    val isLoading: Boolean = true,
    val showLoadingError: Boolean = false,
    val characters: List<CharacterItemUiModel> = emptyList()
)

sealed interface CharactersIntent

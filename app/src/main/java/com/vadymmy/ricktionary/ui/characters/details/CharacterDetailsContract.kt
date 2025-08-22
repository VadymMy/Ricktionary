package com.vadymmy.ricktionary.ui.characters.details

import com.vadymmy.ricktionary.domain.characters.model.Character

data class CharacterDetailsUiState(
    val isLoading: Boolean = true,
    val showLoadingError: Boolean = false,
    val character: Character? = null
)

sealed interface CharacterDetailsIntent

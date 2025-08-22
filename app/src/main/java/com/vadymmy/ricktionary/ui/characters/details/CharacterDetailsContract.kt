package com.vadymmy.ricktionary.ui.characters.details

data class CharacterDetailsUiState(
    val isLoading: Boolean = true,
    val showLoadingError: Boolean = false
)

sealed interface CharacterDetailsIntent

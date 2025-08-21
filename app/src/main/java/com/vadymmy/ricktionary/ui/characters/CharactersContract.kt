package com.vadymmy.ricktionary.ui.characters

data class CharactersUiState(
    val isLoading: Boolean = false
)

sealed interface CharactersIntent

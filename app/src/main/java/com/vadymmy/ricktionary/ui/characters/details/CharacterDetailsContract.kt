package com.vadymmy.ricktionary.ui.characters.details

import com.vadymmy.ricktionary.ui.characters.details.model.CharacterUiModel

data class CharacterDetailsUiState(
    val isLoading: Boolean = true,
    val showLoadingError: Boolean = false,
    val character: CharacterUiModel? = null
)

sealed interface CharacterDetailsIntent {
    data object BackButtonClicked : CharacterDetailsIntent
}

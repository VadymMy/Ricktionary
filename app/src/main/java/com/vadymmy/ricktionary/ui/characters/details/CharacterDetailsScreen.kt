package com.vadymmy.ricktionary.ui.characters.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CharacterDetailsScreen(
    characterDetailsViewModel: CharacterDetailsViewModel = hiltViewModel()
) {
    val uiState by characterDetailsViewModel.uiStateFlow.collectAsState()

    CharacterDetailsScreenContent(
        uiState = uiState,
        onUserIntent = characterDetailsViewModel::onUserIntent
    )
}

@Composable
private fun CharacterDetailsScreenContent(
    uiState: CharacterDetailsUiState,
    onUserIntent: (CharacterDetailsIntent) -> Unit
) {
    Text(text = uiState.character?.name ?: "ERR")
}

@Composable
private fun CharacterDetailsScreenScaffold() {

}

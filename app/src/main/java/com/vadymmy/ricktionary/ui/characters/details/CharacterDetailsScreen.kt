package com.vadymmy.ricktionary.ui.characters.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.vadymmy.ricktionary.R
import com.vadymmy.ricktionary.ui.characters.common.composable.CharacterErrorState
import com.vadymmy.ricktionary.ui.characters.common.preview.CharacterPreview
import com.vadymmy.ricktionary.ui.characters.details.composable.CharacterDetails
import com.vadymmy.ricktionary.ui.characters.details.model.CharacterUiModel
import com.vadymmy.ricktionary.ui.core.composable.TopBarScaffold
import com.vadymmy.ricktionary.ui.theme.AppColors
import com.vadymmy.ricktionary.ui.theme.margin2X

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
    onUserIntent: (CharacterDetailsIntent) -> Unit = {}
) {
    CharacterDetailsScreenScaffold(
        uiState = uiState,
        topBar = {
            TopBarScaffold(
                title = stringResource(id = R.string.character_details_screen_title),
                leadingContent = {
                    IconButton(onClick = {
                        onUserIntent(CharacterDetailsIntent.BackButtonClicked)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = stringResource(id = R.string.back)
                        )
                    }
                }
            )
        },
        content = {
            CharacterDetails(character = it)
        },
        errorState = {
            CharacterErrorState()
        }
    )
}

@Composable
private fun CharacterDetailsScreenScaffold(
    uiState: CharacterDetailsUiState,
    topBar: @Composable () -> Unit = {},
    content: @Composable (CharacterUiModel) -> Unit = {},
    errorState: @Composable () -> Unit = {}
) {
    Scaffold(containerColor = AppColors.Background) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = margin2X)
        ) {
            topBar()

            if (uiState.character != null && !uiState.showLoadingError) {
                content(uiState.character)
            } else {
                errorState()
            }
        }
    }
}

@Composable
@Preview
private fun ErrorCharacterDetailsScreenPreview() {
    CharacterDetailsScreenContent(uiState = CharacterDetailsUiState(isLoading = false, showLoadingError = true))
}

@Composable
@Preview
private fun CharacterDetailsScreenPreview() {
    CharacterDetailsScreenContent(uiState = CharacterDetailsUiState(isLoading = false, character = CharacterPreview.character))
}

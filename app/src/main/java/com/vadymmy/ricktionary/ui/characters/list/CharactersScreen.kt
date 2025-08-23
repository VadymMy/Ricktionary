package com.vadymmy.ricktionary.ui.characters.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.vadymmy.ricktionary.ui.characters.list.composable.CharactersList
import com.vadymmy.ricktionary.ui.characters.common.preview.CharacterPreview
import com.vadymmy.ricktionary.ui.core.LifecycleEffect
import com.vadymmy.ricktionary.ui.core.composable.FeedbackState
import com.vadymmy.ricktionary.ui.core.composable.TopBarScaffold
import com.vadymmy.ricktionary.ui.theme.AppColors
import com.vadymmy.ricktionary.ui.theme.emptyStateImageHeight
import com.vadymmy.ricktionary.ui.theme.margin2X

@Composable
fun CharactersScreen(charactersViewModel: CharactersViewModel = hiltViewModel()) {
    val uiState by charactersViewModel.uiStateFlow.collectAsState()

    LifecycleEffect(onCreate = {
        charactersViewModel.onCreate()
    })

    CharactersScreenContent(
        uiState = uiState,
        onUserIntent = charactersViewModel::onUserIntent
    )
}

@Composable
private fun CharactersScreenContent(
    uiState: CharactersUiState,
    onUserIntent: (CharactersIntent) -> Unit = {}
) {
    CharactersScreenScaffold(
        uiState = uiState,
        topBar = {
            TopBarScaffold(title = stringResource(id = R.string.characters_screen_title))
        },
        content = {
            CharactersList(
                isLoading = uiState.isLoading,
                characters = uiState.characters,
                onCharacterClicked = {
                    onUserIntent(CharactersIntent.CharacterItemClicked(it))
                }
            )
        },
        emptyState = {
            FeedbackState(
                imageModifier = Modifier.height(emptyStateImageHeight),
                title = stringResource(id = R.string.empty_state_title),
                subtitle = stringResource(id = R.string.empty_state_subtitle),
                painter = painterResource(id = R.drawable.ic_portal)
            )
        },
        errorState = {
            CharacterErrorState()
        }
    )
}

@Composable
private fun CharactersScreenScaffold(
    uiState: CharactersUiState,
    topBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit = {},
    emptyState: @Composable () -> Unit = {},
    errorState: @Composable () -> Unit = {}
) {
    Scaffold(containerColor = AppColors.Background) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = margin2X)
        ) {
            topBar()

            when {
                uiState.showLoadingError && uiState.characters.isEmpty() -> {
                    errorState()
                }

                uiState.isLoading || uiState.characters.isNotEmpty() -> {
                    content()
                }

                else -> {
                    emptyState()
                }
            }
        }
    }
}

@Composable
@Preview
private fun EmptyCharactersScreenPreview() {
    CharactersScreenContent(uiState = CharactersUiState(isLoading = false))
}

@Composable
@Preview
private fun ErrorCharactersScreenPreview() {
    CharactersScreenContent(uiState = CharactersUiState(isLoading = false, showLoadingError = true))
}

@Composable
@Preview
private fun LoadingCharactersScreenPreview() {
    CharactersScreenContent(uiState = CharactersUiState(isLoading = true))
}

@Composable
@Preview
private fun CharactersScreenPreview() {
    CharactersScreenContent(
        uiState = CharactersUiState(
            isLoading = false,
            characters = CharacterPreview.characterItems
        )
    )
}

package com.vadymmy.ricktionary.ui.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.vadymmy.ricktionary.ui.characters.composable.CharactersList
import com.vadymmy.ricktionary.ui.characters.preview.CharacterItemsPreview
import com.vadymmy.ricktionary.ui.core.LifecycleEffect
import com.vadymmy.ricktionary.ui.core.composable.FeedbackState
import com.vadymmy.ricktionary.ui.core.composable.TopBarScaffold
import com.vadymmy.ricktionary.ui.theme.AppColors
import com.vadymmy.ricktionary.ui.theme.emptyStateImageHeight
import com.vadymmy.ricktionary.ui.theme.errorStateImageHeight
import com.vadymmy.ricktionary.ui.theme.margin1X

@Composable
fun CharactersScreen(charactersViewModel: CharactersViewModel = hiltViewModel()) {
    val uiState by charactersViewModel.uiStateFlow.collectAsState()

    LifecycleEffect(onResume = {
        charactersViewModel.onResume()
    })

    CharactersScreenContent(
        uiState = uiState,
        onUserIntent = { charactersViewModel.onUserIntent(intent = it) }
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
                characters = uiState.characters
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
            FeedbackState(
                imageModifier = Modifier.height(errorStateImageHeight),
                title = stringResource(id = R.string.error_state_title),
                subtitle = stringResource(id = R.string.error_state_subtitle),
                painter = painterResource(id = R.drawable.ic_sad_morty)
            )
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
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .background(color = AppColors.Background)
                .padding(paddingValues)
        ) {
            topBar()

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(margin1X)
            ) {
                when {
                    uiState.showLoadingError -> {
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
}

@Composable
@Preview
private fun EmptyCharactersScreenPreview() {
    CharactersScreenContent(uiState = CharactersUiState())
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
            characters = CharacterItemsPreview.characterItems
        )
    )
}

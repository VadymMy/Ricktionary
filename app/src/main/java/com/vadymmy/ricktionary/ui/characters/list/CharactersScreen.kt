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
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.vadymmy.ricktionary.R
import com.vadymmy.ricktionary.ui.characters.common.composable.CharacterErrorState
import com.vadymmy.ricktionary.ui.characters.list.composable.CharactersList
import com.vadymmy.ricktionary.ui.characters.common.preview.CharacterPreview
import com.vadymmy.ricktionary.ui.characters.list.model.CharacterItemUiModel
import com.vadymmy.ricktionary.ui.core.LifecycleEffect
import com.vadymmy.ricktionary.ui.core.composable.FeedbackState
import com.vadymmy.ricktionary.ui.core.composable.TopBarScaffold
import com.vadymmy.ricktionary.ui.theme.AppColors
import com.vadymmy.ricktionary.ui.theme.emptyStateImageHeight
import com.vadymmy.ricktionary.ui.theme.margin2X

@Composable
fun CharactersScreen(charactersViewModel: CharactersViewModel = hiltViewModel()) {
    val uiState by charactersViewModel.uiStateFlow.collectAsState()
    val characters = charactersViewModel.charactersFlow.collectAsLazyPagingItems()

    LifecycleEffect(onCreate = {
        charactersViewModel.onCreate()
    })

    CharactersScreenContent(
        uiState = uiState,
        characters = characters,
        onUserIntent = charactersViewModel::onUserIntent
    )
}

@Composable
private fun CharactersScreenContent(
    uiState: CharactersUiState,
    characters: LazyPagingItems<CharacterItemUiModel>,
    onUserIntent: (CharactersIntent) -> Unit = {}
) {
    CharactersScreenScaffold(
        uiState = uiState,
        characters = characters,
        topBar = {
            TopBarScaffold(title = stringResource(id = R.string.characters_screen_title))
        },
        content = { isLoading ->
            CharactersList(
                isLoading = isLoading,
                characters = characters,
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
    characters: LazyPagingItems<CharacterItemUiModel>,
    topBar: @Composable () -> Unit = {},
    content: @Composable (isLoading: Boolean) -> Unit = {},
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

            when (characters.loadState.refresh) {
                is LoadState.Error -> errorState()

                LoadState.Loading -> content(true)

                is LoadState.NotLoading -> {
                    if (characters.itemCount > 0) {
                        content(false)
                    } else {
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
    CharactersScreenContent(
        uiState = CharactersUiState(),
        characters = CharacterPreview.getLazyCharacterItems(),
        onUserIntent = {}
    )
}

@Composable
@Preview
private fun ErrorCharactersScreenPreview() {
    CharactersScreenContent(
        uiState = CharactersUiState(),
        characters = CharacterPreview.getLazyCharacterItems()
    )
}

@Composable
@Preview
private fun LoadingCharactersScreenPreview() {
    CharactersScreenContent(
        uiState = CharactersUiState(),
        characters = CharacterPreview.getLazyCharacterItems()
    )
}

@Composable
@Preview
private fun CharactersScreenPreview() {
    CharactersScreenContent(
        uiState = CharactersUiState(),
        characters = CharacterPreview.getLazyCharacterItems()
    )
}

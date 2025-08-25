package com.vadymmy.ricktionary.ui.characters.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
    val characters = charactersViewModel.charactersFlow.collectAsLazyPagingItems()

    LifecycleEffect(onCreate = {
        charactersViewModel.onCreate()
    })

    CharactersScreenContent(
        characters = characters,
        onUserIntent = charactersViewModel::onUserIntent
    )
}

@Composable
private fun CharactersScreenContent(
    characters: LazyPagingItems<CharacterItemUiModel>,
    onUserIntent: (CharactersIntent) -> Unit = {}
) {
    CharactersScreenScaffold(
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
            CharacterErrorState(onRetryClicked = {
                characters.retry()
            })
        }
    )
}

@Composable
private fun CharactersScreenScaffold(
    characters: LazyPagingItems<CharacterItemUiModel>,
    topBar: @Composable () -> Unit = {},
    content: @Composable (isLoading: Boolean) -> Unit = {},
    emptyState: @Composable () -> Unit = {},
    errorState: @Composable () -> Unit = {}
) {
    val loadState = characters.loadState
    val areItemsPresent = characters.itemCount > 0
    val isEndOfPagination = characters.loadState.append.endOfPaginationReached

    Scaffold(containerColor = AppColors.Background) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = margin2X)
        ) {
            topBar()

            when {
                loadState.refresh is LoadState.Error && !areItemsPresent -> errorState()

                loadState.refresh is LoadState.NotLoading && !areItemsPresent && isEndOfPagination -> emptyState()

                loadState.refresh is LoadState.NotLoading && areItemsPresent -> content(false)

                else -> content(true)
            }
        }
    }
}

@Composable
@Preview
private fun EmptyCharactersScreenPreview() {
    CharactersScreenContent(
        characters = CharacterPreview.getLazyCharacterItems(
            areItemsPresent = false,
            loadState = LoadState.NotLoading(endOfPaginationReached = true)
        )
    )
}

@Composable
@Preview
private fun ErrorCharactersScreenPreview() {
    CharactersScreenContent(
        characters = CharacterPreview.getLazyCharacterItems(
            areItemsPresent = false,
            loadState = LoadState.Error(Exception("Loading error"))
        )
    )
}

@Composable
@Preview
private fun LoadingCharactersScreenPreview() {
    CharactersScreenContent(
        characters = CharacterPreview.getLazyCharacterItems(
            areItemsPresent = false,
            loadState = LoadState.Loading
        )
    )
}

@Composable
@Preview
private fun CharactersScreenPreview() {
    CharactersScreenContent(
        characters = CharacterPreview.getLazyCharacterItems(
            areItemsPresent = true,
            loadState = LoadState.NotLoading(endOfPaginationReached = false)
        )
    )
}

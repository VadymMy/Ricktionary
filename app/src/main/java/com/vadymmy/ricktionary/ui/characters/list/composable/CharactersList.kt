package com.vadymmy.ricktionary.ui.characters.list.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vadymmy.ricktionary.ui.characters.list.model.CharacterItemUiModel
import com.vadymmy.ricktionary.ui.characters.common.preview.CharacterPreview
import com.vadymmy.ricktionary.ui.theme.margin2X

private const val LOADING_ITEMS_SIZE = 10
private const val NO_CHARACTERS = 0

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersList(
    isLoading: Boolean,
    charactersCount: Int,
    getCharacter: (index: Int) -> CharacterItemUiModel?,
    onPulledToRefresh: () -> Unit = {},
    onCharacterClicked: (CharacterItemUiModel) -> Unit = {}
) {
    val pullToRefreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        isRefreshing = isLoading && charactersCount > NO_CHARACTERS,
        state = pullToRefreshState,
        onRefresh = onPulledToRefresh
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(margin2X)) {
            if (isLoading && charactersCount == NO_CHARACTERS) {
                items(LOADING_ITEMS_SIZE) {
                    CharacterLoadingItem()
                }
            } else {
                items(charactersCount) {
                    val item = getCharacter(it) ?: return@items

                    CharacterItem(
                        item = item,
                        onClick = { onCharacterClicked(item) }
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun CharactersListLoadingPreview() {
    CharactersList(
        isLoading = true,
        charactersCount = 0,
        getCharacter = { null }
    )
}

@Composable
@Preview
private fun CharactersListRefreshLoadingPreview() {
    CharactersList(
        isLoading = true,
        charactersCount = CharacterPreview.characterItems.size,
        getCharacter = {
            CharacterPreview.characterItems[it]
        }
    )
}

@Composable
@Preview
private fun CharactersListPreview() {
    CharactersList(
        isLoading = false,
        charactersCount = CharacterPreview.characterItems.size,
        getCharacter = {
            CharacterPreview.characterItems[it]
        }
    )
}

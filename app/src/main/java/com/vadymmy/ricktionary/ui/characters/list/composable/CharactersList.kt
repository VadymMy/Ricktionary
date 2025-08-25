package com.vadymmy.ricktionary.ui.characters.list.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vadymmy.ricktionary.ui.characters.list.model.CharacterItemUiModel
import com.vadymmy.ricktionary.ui.characters.common.preview.CharacterPreview
import com.vadymmy.ricktionary.ui.theme.margin2X

private const val LOADING_ITEMS_SIZE = 10

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersList(
    isLoading: Boolean,
    characters: List<CharacterItemUiModel>,
    onPulledToRefresh: () -> Unit = {},
    onCharacterClicked: (CharacterItemUiModel) -> Unit = {}
) {
    val pullToRefreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        isRefreshing = isLoading && characters.isNotEmpty(),
        state = pullToRefreshState,
        onRefresh = onPulledToRefresh
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(margin2X)) {
            if (isLoading && characters.isEmpty()) {
                items(LOADING_ITEMS_SIZE) {
                    CharacterLoadingItem()
                }
            } else {
                items(characters, key = { it.id }) {
                    CharacterItem(
                        item = it,
                        onClick = { onCharacterClicked(it) }
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
        characters = emptyList()
    )
}

@Composable
@Preview
private fun CharactersListRefreshLoadingPreview() {
    CharactersList(
        isLoading = true,
        characters = CharacterPreview.characterItems
    )
}

@Composable
@Preview
private fun CharactersListPreview() {
    CharactersList(
        isLoading = false,
        characters = CharacterPreview.characterItems
    )
}

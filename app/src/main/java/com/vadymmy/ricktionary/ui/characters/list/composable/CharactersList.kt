package com.vadymmy.ricktionary.ui.characters.list.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import com.vadymmy.ricktionary.ui.characters.list.model.CharacterItemUiModel
import com.vadymmy.ricktionary.ui.characters.common.preview.CharacterPreview
import com.vadymmy.ricktionary.ui.theme.margin2X

private const val LOADING_ITEMS_SIZE = 10

@Composable
fun CharactersList(
    isLoading: Boolean,
    characters: LazyPagingItems<CharacterItemUiModel>,
    onCharacterClicked: (CharacterItemUiModel) -> Unit = {}
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(margin2X)) {
        if (isLoading) {
            items(LOADING_ITEMS_SIZE) {
                CharacterLoadingItem()
            }
        } else {
            items(characters.itemCount) { index ->
                val character = characters[index] ?: return@items

                CharacterItem(
                    item = character,
                    onClick = { onCharacterClicked(character) }
                )
            }
        }
    }
}

@Composable
@Preview
private fun CharactersListLoadingPreview() {
    CharactersList(
        isLoading = true,
        characters = CharacterPreview.getLazyCharacterItems()
    )
}

@Composable
@Preview
private fun CharactersListPreview() {
    CharactersList(
        isLoading = false,
        characters = CharacterPreview.getLazyCharacterItems()
    )
}

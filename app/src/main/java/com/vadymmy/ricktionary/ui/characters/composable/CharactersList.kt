package com.vadymmy.ricktionary.ui.characters.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vadymmy.ricktionary.ui.characters.model.CharacterItemUiModel
import com.vadymmy.ricktionary.ui.characters.preview.CharacterItemsPreview
import com.vadymmy.ricktionary.ui.theme.margin1X

private const val LOADING_ITEMS_SIZE = 10

@Composable
fun CharactersList(
    isLoading: Boolean,
    characters: List<CharacterItemUiModel>
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(margin1X)) {
        if (isLoading) {
            items(LOADING_ITEMS_SIZE) {
                CharacterLoadingItem()
            }
        } else {
            items(characters, key = { it.id }) { character ->
                CharacterItem(item = character)
            }
        }
    }
}

@Composable
@Preview
private fun CharactersListLoadingPreview() {
    CharactersList(isLoading = true, characters = emptyList())
}

@Composable
@Preview
private fun CharactersListPreview() {
    CharactersList(isLoading = false, characters = CharacterItemsPreview.characterItems)
}

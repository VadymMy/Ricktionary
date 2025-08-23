package com.vadymmy.ricktionary.ui.characters.list.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vadymmy.ricktionary.ui.characters.list.model.CharacterItemUiModel
import com.vadymmy.ricktionary.ui.characters.common.preview.CharacterPreview
import com.vadymmy.ricktionary.ui.theme.margin1X
import com.vadymmy.ricktionary.ui.theme.margin1_5X
import com.vadymmy.ricktionary.ui.theme.margin2X

private const val LOADING_ITEMS_SIZE = 10

@Composable
fun CharactersList(
    isLoading: Boolean,
    characters: List<CharacterItemUiModel>,
    onCharacterClicked: (CharacterItemUiModel) -> Unit = {}
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(margin2X)) {
        if (isLoading) {
            items(LOADING_ITEMS_SIZE) {
                CharacterLoadingItem()
            }
        } else {
            items(characters, key = { it.id }) { character ->
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
        characters = emptyList()
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

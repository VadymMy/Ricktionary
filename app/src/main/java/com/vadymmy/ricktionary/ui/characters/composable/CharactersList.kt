package com.vadymmy.ricktionary.ui.characters.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vadymmy.ricktionary.ui.characters.model.CharacterItemUiModel
import com.vadymmy.ricktionary.ui.characters.preview.CharacterItemsPreview
import com.vadymmy.ricktionary.ui.theme.margin1X

@Composable
fun CharactersList(
    characters: List<CharacterItemUiModel>
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(margin1X)) {
        items(characters, key = { it.id }) { character ->
            CharacterItem(item = character)
        }
    }
}

@Composable
@Preview
private fun CharactersListPreview() {
    CharactersList(characters = CharacterItemsPreview.characterItems)
}

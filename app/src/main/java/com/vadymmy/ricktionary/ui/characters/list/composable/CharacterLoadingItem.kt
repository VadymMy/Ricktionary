package com.vadymmy.ricktionary.ui.characters.list.composable

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vadymmy.ricktionary.ui.core.animation.shimmerLoading
import com.vadymmy.ricktionary.ui.core.composable.ItemCard
import com.vadymmy.ricktionary.ui.theme.characterItemImageSize

@Composable
fun CharacterLoadingItem() {
    ItemCard(
        modifier = Modifier
            .height(characterItemImageSize)
            .shimmerLoading()
    )
}

@Composable
@Preview
private fun CharacterLoadingItemPreview() {
    CharacterLoadingItem()
}

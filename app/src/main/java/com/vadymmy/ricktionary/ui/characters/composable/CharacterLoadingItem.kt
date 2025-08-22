package com.vadymmy.ricktionary.ui.characters.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.vadymmy.ricktionary.ui.core.animation.shimmerLoading
import com.vadymmy.ricktionary.ui.theme.AppColors
import com.vadymmy.ricktionary.ui.theme.cardContainerShapeDefault
import com.vadymmy.ricktionary.ui.theme.characterItemContainerHeight

@Composable
fun CharacterLoadingItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(characterItemContainerHeight)
            .background(color = AppColors.ItemBackground, shape = cardContainerShapeDefault)
            .clip(shape = cardContainerShapeDefault)
            .shimmerLoading()
    )
}

@Composable
@Preview
private fun CharacterLoadingItemPreview() {
    CharacterLoadingItem()
}

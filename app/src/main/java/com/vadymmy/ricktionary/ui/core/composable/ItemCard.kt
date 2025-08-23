package com.vadymmy.ricktionary.ui.core.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.vadymmy.ricktionary.ui.theme.AppColors
import com.vadymmy.ricktionary.ui.theme.cardContainerShapeDefault
import com.vadymmy.ricktionary.ui.theme.characterItemImageSize

@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = AppColors.ItemBackground, shape = cardContainerShapeDefault)
            .clip(shape = cardContainerShapeDefault)
    ) {
        content()
    }
}

@Composable
@Preview
private fun ItemCardPreview() {
    ItemCard(modifier = Modifier.height(characterItemImageSize))
}

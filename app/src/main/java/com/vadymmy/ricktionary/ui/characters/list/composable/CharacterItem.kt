package com.vadymmy.ricktionary.ui.characters.list.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.vadymmy.ricktionary.R
import com.vadymmy.ricktionary.ui.characters.common.composable.CharacterGenderPill
import com.vadymmy.ricktionary.ui.characters.common.composable.CharacterStatusPill
import com.vadymmy.ricktionary.ui.characters.list.model.CharacterItemUiModel
import com.vadymmy.ricktionary.ui.characters.list.preview.CharacterItemsPreview
import com.vadymmy.ricktionary.ui.theme.AppColors
import com.vadymmy.ricktionary.ui.theme.Typography
import com.vadymmy.ricktionary.ui.theme.cardContainerShapeDefault
import com.vadymmy.ricktionary.ui.theme.characterItemContainerHeight
import com.vadymmy.ricktionary.ui.theme.characterItemImageSize
import com.vadymmy.ricktionary.ui.theme.margin1X
import com.vadymmy.ricktionary.ui.theme.margin1_5X
import com.vadymmy.ricktionary.ui.theme.marginHalf

@Composable
fun CharacterItem(
    item: CharacterItemUiModel,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(characterItemContainerHeight)
            .background(color = AppColors.ItemBackground, shape = cardContainerShapeDefault)
            .clip(shape = cardContainerShapeDefault)
            .clickable(onClick = onClick)
            .padding(margin1_5X)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                modifier = Modifier
                    .size(characterItemImageSize)
                    .background(color = AppColors.Background, shape = cardContainerShapeDefault)
                    .clip(cardContainerShapeDefault),
                model = item.imageUrl,
                contentDescription = item.name,
                contentScale = ContentScale.Inside,
                placeholder = painterResource(id = R.drawable.ic_character_placeholder),
                error = painterResource(id = R.drawable.ic_character_placeholder)
            )

            Column(
                modifier = Modifier.padding(margin1X),
                verticalArrangement = Arrangement.spacedBy(margin1X)
            ) {
                Text(text = item.name, style = Typography.bodyLarge, color = AppColors.TextPrimary)

                LocationText(location = item.location)

                Row(horizontalArrangement = Arrangement.spacedBy(margin1X)) {
                    CharacterStatusPill(status = item.status)
                    CharacterGenderPill(gender = item.gender)
                }
            }
        }
    }
}

@Composable
private fun LocationText(location: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(marginHalf),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_location),
            tint = AppColors.TextSecondary,
            contentDescription = null
        )

        Text(text = location, style = Typography.bodySmall, color = AppColors.TextSecondary)
    }
}

@Composable
@Preview
private fun CharacterItemPreview() {
    Column(verticalArrangement = Arrangement.spacedBy(margin1X)) {
        CharacterItemsPreview.characterItems.forEach {
            CharacterItem(item = it)
        }
    }
}

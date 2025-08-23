package com.vadymmy.ricktionary.ui.characters.list.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vadymmy.ricktionary.R
import com.vadymmy.ricktionary.ui.characters.common.composable.CharacterAttributeRow
import com.vadymmy.ricktionary.ui.characters.common.composable.CharacterGenderPill
import com.vadymmy.ricktionary.ui.characters.common.composable.CharacterImage
import com.vadymmy.ricktionary.ui.characters.common.composable.CharacterStatusPill
import com.vadymmy.ricktionary.ui.characters.list.model.CharacterItemUiModel
import com.vadymmy.ricktionary.ui.characters.common.preview.CharacterPreview
import com.vadymmy.ricktionary.ui.core.composable.ItemCard
import com.vadymmy.ricktionary.ui.theme.AppColors
import com.vadymmy.ricktionary.ui.theme.Typography
import com.vadymmy.ricktionary.ui.theme.characterItemImageSize
import com.vadymmy.ricktionary.ui.theme.margin1X
import com.vadymmy.ricktionary.ui.theme.shapeCharacterCardImage

@Composable
fun CharacterItem(
    item: CharacterItemUiModel,
    onClick: () -> Unit = {}
) {
    ItemCard(
        modifier = Modifier
            .height(characterItemImageSize)
            .clickable(onClick = onClick)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CharacterImage(
                modifier = Modifier
                    .background(color = AppColors.Background, shape = shapeCharacterCardImage)
                    .clip(shapeCharacterCardImage)
                    .size(characterItemImageSize),
                imageUrl = item.imageUrl,
                contentDescription = item.name
            )

            Column(
                modifier = Modifier.padding(margin1X),
                verticalArrangement = Arrangement.spacedBy(margin1X)
            ) {
                Text(text = item.name, style = Typography.bodyLarge, color = AppColors.TextPrimary)

                CharacterAttributeRow(
                    text = item.location,
                    painter = painterResource(id = R.drawable.ic_location),
                    contentDescription = stringResource(id = R.string.character_details_location_title)
                )

                Row(
                    modifier = Modifier.padding(top = margin1X),
                    horizontalArrangement = Arrangement.spacedBy(margin1X)
                ) {
                    CharacterStatusPill(status = item.status)
                    CharacterGenderPill(gender = item.gender)
                }
            }
        }
    }
}

@Composable
@Preview
private fun CharacterItemPreview() {
    Column(verticalArrangement = Arrangement.spacedBy(margin1X)) {
        CharacterPreview.characterItems.forEach {
            CharacterItem(item = it)
        }
    }
}

package com.vadymmy.ricktionary.ui.characters.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.vadymmy.ricktionary.ui.characters.model.CharacterItemUiModel
import com.vadymmy.ricktionary.ui.characters.preview.CharacterItemsPreview
import com.vadymmy.ricktionary.ui.theme.AppColors
import com.vadymmy.ricktionary.ui.theme.Typography
import com.vadymmy.ricktionary.ui.theme.cardContainerShapeDefault
import com.vadymmy.ricktionary.ui.theme.characterItemImageSize
import com.vadymmy.ricktionary.ui.theme.margin1X
import com.vadymmy.ricktionary.ui.theme.marginHalf
import com.vadymmy.ricktionary.ui.theme.shapeImageDefault

@Composable
fun CharacterItem(itemUiModel: CharacterItemUiModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = AppColors.ItemBackground, shape = cardContainerShapeDefault)
            .padding(margin1X)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                modifier = Modifier
                    .size(characterItemImageSize)
                    .background(color = AppColors.Background, shape = shapeImageDefault)
                    .clip(shapeImageDefault),
                model = itemUiModel.imageUrl,
                contentDescription = itemUiModel.name,
                contentScale = ContentScale.Inside,
                placeholder = painterResource(id = R.drawable.ic_person_loading_placeholder),
                error = painterResource(id = R.drawable.ic_person_loading_placeholder)
            )

            Column(
                modifier = Modifier.padding(margin1X),
                verticalArrangement = Arrangement.spacedBy(margin1X)
            ) {
                Text(text = itemUiModel.name, style = Typography.bodyLarge, color = AppColors.TextPrimary)
                LocationText(location = itemUiModel.location)
                CharacterStatusPill(statusUiModel = itemUiModel.status)
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
            CharacterItem(itemUiModel = it)
        }
    }

}

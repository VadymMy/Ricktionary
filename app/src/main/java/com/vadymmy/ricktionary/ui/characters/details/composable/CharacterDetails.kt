package com.vadymmy.ricktionary.ui.characters.details.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vadymmy.ricktionary.R
import com.vadymmy.ricktionary.ui.characters.common.composable.CharacterAttributeRow
import com.vadymmy.ricktionary.ui.characters.common.composable.CharacterGenderPill
import com.vadymmy.ricktionary.ui.characters.common.composable.CharacterImage
import com.vadymmy.ricktionary.ui.characters.common.composable.CharacterStatusPill
import com.vadymmy.ricktionary.ui.characters.common.preview.CharacterPreview
import com.vadymmy.ricktionary.ui.characters.details.model.CharacterUiModel
import com.vadymmy.ricktionary.ui.core.composable.InformationPill
import com.vadymmy.ricktionary.ui.theme.AppColors
import com.vadymmy.ricktionary.ui.theme.Typography
import com.vadymmy.ricktionary.ui.theme.cardContainerShapeDefault
import com.vadymmy.ricktionary.ui.theme.margin1X
import com.vadymmy.ricktionary.ui.theme.marginHalf

@Composable
fun CharacterDetails(character: CharacterUiModel) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(margin1X)
    ) {
        CharacterImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(cardContainerShapeDefault),
            imageUrl = character.imageUrl,
            contentDescription = character.name
        )

        Text(
            modifier = Modifier.padding(top = margin1X),
            text = character.name,
            style = Typography.titleLarge,
            color = AppColors.TextDark
        )

        CharacterDetailsPills(character = character)

        CharacterAttributeInformation(
            attributeTitle = stringResource(id = R.string.character_details_origin_title),
            attributeValue = character.origin,
            painter = painterResource(id = R.drawable.ic_origin)
        )

        CharacterAttributeInformation(
            attributeTitle = stringResource(id = R.string.character_details_location_title),
            attributeValue = character.location,
            painter = painterResource(id = R.drawable.ic_location)
        )
    }
}

@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun CharacterDetailsPills(character: CharacterUiModel) {
    FlowRow(
        verticalArrangement = Arrangement.spacedBy(margin1X),
        horizontalArrangement = Arrangement.spacedBy(margin1X)
    ) {
        CharacterStatusPill(status = character.status)

        CharacterGenderPill(gender = character.gender)

        if (character.species.isNotEmpty()) {
            InformationPill(color = AppColors.Teal, text = character.species)
        }

        if (character.type.isNotEmpty()) {
            InformationPill(color = AppColors.Brown, text = character.type)
        }

        InformationPill(
            color = AppColors.Grey,
            text = stringResource(id = R.string.character_details_episodes_number, character.episodesNumber)
        )
    }
}

@Composable
private fun CharacterAttributeInformation(
    attributeTitle: String,
    attributeValue: String,
    painter: Painter
) {
    if (attributeValue.isBlank()) return
    val unknownText = stringResource(id = R.string.unknown)

    Spacer(modifier = Modifier.padding(marginHalf))

    CharacterAttributeRow(
        text = attributeTitle,
        painter = painter,
        contentDescription = attributeTitle
    )

    Text(
        modifier = Modifier.padding(start = marginHalf),
        text = if (attributeValue.equals(unknownText, ignoreCase = true)) unknownText else attributeValue,
        style = Typography.bodyLarge
    )
}

@Composable
@Preview
private fun CharacterDetailsPreview() {
    Surface {
        CharacterDetails(character = CharacterPreview.character)
    }
}

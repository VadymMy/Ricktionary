package com.vadymmy.ricktionary.ui.characters.common.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vadymmy.ricktionary.R
import com.vadymmy.ricktionary.ui.characters.common.preview.CharacterPreview
import com.vadymmy.ricktionary.ui.theme.AppColors
import com.vadymmy.ricktionary.ui.theme.Typography
import com.vadymmy.ricktionary.ui.theme.characterAttributeImageSize
import com.vadymmy.ricktionary.ui.theme.marginHalf

@Composable
fun CharacterAttributeRow(
    text: String,
    painter: Painter,
    contentDescription: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(marginHalf),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(characterAttributeImageSize),
            painter = painter,
            tint = AppColors.TextSecondary,
            contentDescription = contentDescription
        )

        Text(text = text, style = Typography.bodySmall, color = AppColors.TextSecondary)
    }
}

@Composable
@Preview
private fun CharacterAttributeRowPreview() {
    Surface {
        CharacterAttributeRow(
            text = CharacterPreview.character.location,
            painter = painterResource(id = R.drawable.ic_location),
            contentDescription = stringResource(id = R.string.character_details_location_title)
        )
    }
}

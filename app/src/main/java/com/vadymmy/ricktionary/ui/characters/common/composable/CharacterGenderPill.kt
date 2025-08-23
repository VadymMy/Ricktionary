package com.vadymmy.ricktionary.ui.characters.common.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vadymmy.ricktionary.ui.characters.common.model.CharacterGenderUiModel
import com.vadymmy.ricktionary.ui.core.composable.InformationPill
import com.vadymmy.ricktionary.ui.theme.genderPillImageSize
import com.vadymmy.ricktionary.ui.theme.margin1X

@Composable
fun CharacterGenderPill(gender: CharacterGenderUiModel) {
    InformationPill(
        color = gender.color,
        text = stringResource(id = gender.stringRes),
        leadingContent = {
            Icon(
                modifier = Modifier.size(genderPillImageSize),
                painter = painterResource(id = gender.iconRes),
                tint = gender.color,
                contentDescription = stringResource(id = gender.stringRes)
            )
        }
    )
}

@Composable
@Preview
private fun CharacterStatusPillPreview() {
    Column(verticalArrangement = Arrangement.spacedBy(margin1X)) {
        CharacterGenderUiModel.entries.forEach {
            CharacterGenderPill(gender = it)
        }
    }
}

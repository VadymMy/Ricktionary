package com.vadymmy.ricktionary.ui.characters.common.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vadymmy.ricktionary.ui.characters.common.model.CharacterStatusUiModel
import com.vadymmy.ricktionary.ui.core.composable.InformationPill
import com.vadymmy.ricktionary.ui.theme.margin1X
import com.vadymmy.ricktionary.ui.theme.statusPillCircleSize


@Composable
fun CharacterStatusPill(status: CharacterStatusUiModel) {
    InformationPill(
        color = status.color,
        text = stringResource(id = status.stringRes),
        leadingContent = {
            Box(
                modifier = Modifier
                    .size(statusPillCircleSize)
                    .background(
                        color = status.color,
                        shape = CircleShape
                    )
            )
        }
    )
}

@Composable
@Preview
private fun CharacterStatusPillPreview() {
    Column(verticalArrangement = Arrangement.spacedBy(margin1X)) {
        CharacterStatusUiModel.entries.forEach {
            CharacterStatusPill(status = it)
        }
    }
}

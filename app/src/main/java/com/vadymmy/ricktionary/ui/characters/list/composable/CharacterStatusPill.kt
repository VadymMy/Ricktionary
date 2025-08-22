package com.vadymmy.ricktionary.ui.characters.list.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vadymmy.ricktionary.ui.characters.list.model.CharacterStatusUiModel
import com.vadymmy.ricktionary.ui.theme.Typography
import com.vadymmy.ricktionary.ui.theme.borderWidthDefault
import com.vadymmy.ricktionary.ui.theme.margin1X
import com.vadymmy.ricktionary.ui.theme.marginHalf
import com.vadymmy.ricktionary.ui.theme.pillContainerHeight
import com.vadymmy.ricktionary.ui.theme.pillContainerShapeDefault
import com.vadymmy.ricktionary.ui.theme.statusPillCircleSize

private const val PILL_BG_ALPHA = 0.25f

@Composable
fun CharacterStatusPill(
    statusUiModel: CharacterStatusUiModel
) {
    Row(
        modifier = Modifier
            .height(pillContainerHeight)
            .background(
                color = statusUiModel.color.copy(alpha = PILL_BG_ALPHA),
                shape = pillContainerShapeDefault
            )
            .border(
                width = borderWidthDefault,
                color = statusUiModel.color,
                shape = pillContainerShapeDefault
            )
            .padding(horizontal = margin1X, vertical = marginHalf),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(marginHalf)
    ) {
        Box(
            modifier = Modifier
                .size(statusPillCircleSize)
                .background(
                    color = statusUiModel.color,
                    shape = CircleShape
                )
        )

        Text(
            text = stringResource(id = statusUiModel.stringRes),
            style = Typography.bodySmall,
            color = statusUiModel.color
        )
    }
}

@Composable
@Preview
private fun CharacterStatusPillPreview() {
    Column(verticalArrangement = Arrangement.spacedBy(margin1X)) {
        CharacterStatusUiModel.entries.forEach {
            CharacterStatusPill(statusUiModel = it)
        }
    }
}

package com.vadymmy.ricktionary.ui.core.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vadymmy.ricktionary.R
import com.vadymmy.ricktionary.ui.theme.AppColors
import com.vadymmy.ricktionary.ui.theme.Typography
import com.vadymmy.ricktionary.ui.theme.borderWidthDefault
import com.vadymmy.ricktionary.ui.theme.margin1X
import com.vadymmy.ricktionary.ui.theme.marginHalf
import com.vadymmy.ricktionary.ui.theme.pillContainerHeight
import com.vadymmy.ricktionary.ui.theme.pillContainerShapeDefault

private const val PILL_BG_ALPHA = 0.25f

@Composable
fun InformationPill(
    color: Color,
    text: String,
    leadingContent: @Composable () -> Unit ={}
) {
    Row(
        modifier = Modifier
            .height(pillContainerHeight)
            .background(
                color = color.copy(alpha = PILL_BG_ALPHA),
                shape = pillContainerShapeDefault
            )
            .border(
                width = borderWidthDefault,
                color = color,
                shape = pillContainerShapeDefault
            )
            .padding(horizontal = margin1X, vertical = marginHalf),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(marginHalf)
    ) {
        leadingContent()

        Text(
            text = text,
            style = Typography.bodySmall,
            color = color
        )
    }
}

@Composable
@Preview
private fun InformationPillPreview() {
    InformationPill(
        color = AppColors.YellowDark,
        text = stringResource(id = R.string.unknown)
    )
}

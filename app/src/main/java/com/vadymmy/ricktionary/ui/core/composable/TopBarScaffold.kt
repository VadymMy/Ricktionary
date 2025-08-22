package com.vadymmy.ricktionary.ui.core.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.vadymmy.ricktionary.R
import com.vadymmy.ricktionary.ui.theme.AppColors
import com.vadymmy.ricktionary.ui.theme.Typography
import com.vadymmy.ricktionary.ui.theme.margin1X
import com.vadymmy.ricktionary.ui.theme.topBarHeight
import com.vadymmy.ricktionary.ui.theme.topBarImageSize

@Composable
fun TopBarScaffold(
    title: String,
    leadingContent: @Composable () -> Unit = {},
    trailingContent: @Composable () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(topBarHeight)
            .padding(margin1X),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(margin1X)
    ) {
        leadingContent()

        Text(
            modifier = Modifier.weight(1f),
            text = title,
            style = Typography.titleLarge,
            color = AppColors.TextDark,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        trailingContent()
    }
}

@Composable
@Preview
private fun TopBarPreview() {
    Box(modifier = Modifier.background(color = Color.White)) {
        TopBarScaffold(title = "Characters")
    }
}

@Composable
@Preview
private fun TopBarWithIconPreview() {
    Box(modifier = Modifier.background(color = Color.White)) {
        TopBarScaffold(
            title = "Locations",
            leadingContent = {
                Icon(
                    modifier = Modifier.size(topBarImageSize),
                    painter = painterResource(id = R.drawable.ic_location),
                    contentDescription = null
                )
            }
        )
    }
}

package com.vadymmy.ricktionary.ui.characters.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vadymmy.ricktionary.R
import com.vadymmy.ricktionary.ui.theme.Typography
import com.vadymmy.ricktionary.ui.theme.emptyStateImageHeight
import com.vadymmy.ricktionary.ui.theme.margin2X

@Composable
fun CharactersEmptyState() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(margin2X),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(margin2X),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .padding(bottom = margin2X)
                    .height(emptyStateImageHeight),
                painter = painterResource(id = R.drawable.ic_portal),
                contentDescription = stringResource(id = R.string.empty_state_title)
            )

            Text(
                text = stringResource(id = R.string.empty_state_title),
                style = Typography.titleMedium
            )

            Text(
                text = stringResource(id = R.string.empty_state_subtitle),
                style = Typography.bodyLarge
            )
        }
    }
}

@Composable
@Preview
private fun CharactersEmptyStatePreview() {
    Box(modifier = Modifier.background(color = Color.White)) {
        CharactersEmptyState()
    }
}

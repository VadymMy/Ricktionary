package com.vadymmy.ricktionary.ui.core.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.vadymmy.ricktionary.R
import com.vadymmy.ricktionary.ui.theme.Typography
import com.vadymmy.ricktionary.ui.theme.margin2X
import com.vadymmy.ricktionary.ui.theme.defaultImageSize

@Composable
fun FeedbackState(
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    painter: Painter
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(margin2X),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = imageModifier,
                painter = painter,
                contentDescription = title
            )

            Spacer(modifier = Modifier.height(margin2X))

            Text(text = title, style = Typography.titleMedium)

            Text(text = subtitle, style = Typography.bodyLarge)
        }
    }
}

@Composable
@Preview
private fun FeedbackStatePreview() {
    Box(modifier = Modifier.background(color = Color.White)) {
        FeedbackState(
            imageModifier = Modifier.size(defaultImageSize),
            title = "Location unknown",
            subtitle = "Please try again.",
            painter = painterResource(id = R.drawable.ic_location)
        )
    }
}

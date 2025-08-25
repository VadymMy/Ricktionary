package com.vadymmy.ricktionary.ui.core.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vadymmy.ricktionary.R
import com.vadymmy.ricktionary.ui.theme.AppColors
import com.vadymmy.ricktionary.ui.theme.Typography
import com.vadymmy.ricktionary.ui.theme.buttonHeight
import com.vadymmy.ricktionary.ui.theme.buttonImageSize
import com.vadymmy.ricktionary.ui.theme.buttonShapeDefault
import com.vadymmy.ricktionary.ui.theme.margin1X

@Composable
fun Button(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
    leadingContent: @Composable () -> Unit = {}
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(buttonHeight),
        enabled = isEnabled,
        shape = buttonShapeDefault,
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = AppColors.GreenPrimary,
            disabledContainerColor = AppColors.ButtonDisabled
        ),
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(margin1X)
        ) {
            leadingContent()

            Text(
                text = text,
                color = AppColors.White,
                style = Typography.bodyLarge
            )
        }
    }
}

@Composable
@Preview
private fun ButtonPreview() {
    Button(
        text = stringResource(id = R.string.retry),
        onClick = {}
    )
}

@Composable
@Preview
private fun ButtonPreviewDisabled() {
    Button(
        text = stringResource(id = R.string.retry),
        isEnabled = false,
        onClick = {}
    )
}

@Composable
@Preview
private fun ButtonWithIconPreview() {
    Button(
        text = stringResource(id = R.string.retry),
        leadingContent = {
            Icon(
                modifier = Modifier.size(buttonImageSize),
                painter = painterResource(id = R.drawable.ic_retry),
                contentDescription = stringResource(id = R.string.retry)
            )
        },
        onClick = {}
    )
}

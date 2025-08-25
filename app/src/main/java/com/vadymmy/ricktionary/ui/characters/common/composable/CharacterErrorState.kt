package com.vadymmy.ricktionary.ui.characters.common.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vadymmy.ricktionary.R
import com.vadymmy.ricktionary.ui.core.composable.Button
import com.vadymmy.ricktionary.ui.core.composable.FeedbackState
import com.vadymmy.ricktionary.ui.theme.buttonImageSize
import com.vadymmy.ricktionary.ui.theme.errorStateImageHeight
import com.vadymmy.ricktionary.ui.theme.margin2X

@Composable
fun CharacterErrorState(onRetryClicked: () -> Unit = {}) {
    Box(modifier = Modifier.fillMaxSize()) {
        FeedbackState(
            imageModifier = Modifier.height(errorStateImageHeight),
            title = stringResource(id = R.string.error_state_title),
            subtitle = stringResource(id = R.string.error_state_subtitle),
            painter = painterResource(id = R.drawable.ic_sad_morty)
        )

        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = margin2X),
            text = stringResource(id = R.string.retry),
            leadingContent = {
                Icon(
                    modifier = Modifier.size(buttonImageSize),
                    painter = painterResource(id = R.drawable.ic_retry),
                    contentDescription = stringResource(id = R.string.retry)
                )
            },
            onClick = onRetryClicked
        )
    }
}

@Composable
@Preview
private fun CharacterErrorStatePreview() {
    Surface {
        CharacterErrorState()
    }
}

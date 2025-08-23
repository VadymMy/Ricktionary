package com.vadymmy.ricktionary.ui.characters.common.composable

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vadymmy.ricktionary.R
import com.vadymmy.ricktionary.ui.core.composable.FeedbackState
import com.vadymmy.ricktionary.ui.theme.errorStateImageHeight

@Composable
fun CharacterErrorState() {
    FeedbackState(
        imageModifier = Modifier.height(errorStateImageHeight),
        title = stringResource(id = R.string.error_state_title),
        subtitle = stringResource(id = R.string.error_state_subtitle),
        painter = painterResource(id = R.drawable.ic_sad_morty)
    )
}

@Composable
@Preview
private fun CharacterErrorStatePreview() {
    Surface {
        CharacterErrorState()
    }
}

package com.vadymmy.ricktionary.ui.characters.common.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.vadymmy.ricktionary.R
import com.vadymmy.ricktionary.ui.characters.common.preview.CharacterPreview

@Composable
fun CharacterImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentDescription: String
) {
    AsyncImage(
        modifier = modifier,
        model = imageUrl,
        contentDescription = contentDescription,
        placeholder = painterResource(id = R.drawable.ic_character_placeholder),
        error = painterResource(id = R.drawable.ic_character_placeholder)
    )
}

@Composable
@Preview
private fun CharacterImagePreview() {
    CharacterImage(
        imageUrl = CharacterPreview.character.imageUrl,
        contentDescription = CharacterPreview.character.name
    )
}

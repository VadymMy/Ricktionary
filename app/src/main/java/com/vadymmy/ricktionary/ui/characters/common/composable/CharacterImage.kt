package com.vadymmy.ricktionary.ui.characters.common.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.vadymmy.ricktionary.R
import com.vadymmy.ricktionary.ui.characters.common.preview.CharacterPreview
import com.vadymmy.ricktionary.ui.theme.shapeImageDefault

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
        contentScale = ContentScale.Inside,
        placeholder = painterResource(id = R.drawable.ic_character_placeholder),
        error = painterResource(id = R.drawable.ic_character_placeholder)
    )
}

@Composable
@Preview
private fun CharacterImagePreview() {
    CharacterImage(
        modifier = Modifier.clip(shapeImageDefault),
        imageUrl = CharacterPreview.character.imageUrl,
        contentDescription = CharacterPreview.character.name
    )
}

package com.vadymmy.ricktionary.ui.characters.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CharacterDetailsScreen(
    id: Int,
    characterDetailsViewModel: CharacterDetailsViewModel = hiltViewModel()
) {
    Box(Modifier.fillMaxSize()) {

    }
}

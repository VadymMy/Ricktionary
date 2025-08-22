package com.vadymmy.ricktionary.ui.characters

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.vadymmy.ricktionary.R
import com.vadymmy.ricktionary.ui.characters.composable.CharactersEmptyState
import com.vadymmy.ricktionary.ui.core.LifecycleEffect
import com.vadymmy.ricktionary.ui.core.TopBarScaffold

@Composable
fun CharactersScreen(charactersViewModel: CharactersViewModel = hiltViewModel()) {
    val uiState by charactersViewModel.uiStateFlow.collectAsState()

    LifecycleEffect(onResume = {
        charactersViewModel.onResume()
    })

    CharactersScreenContent(
        uiState = uiState,
        onUserIntent = { charactersViewModel.onUserIntent(intent = it) }
    )
}

@Composable
private fun CharactersScreenContent(
    uiState: CharactersUiState,
    onUserIntent: (CharactersIntent) -> Unit = {}
) {
    CharactersScreenScaffold(
        uiState = uiState,
        topBar = {
            TopBarScaffold(title = stringResource(id = R.string.characters_screen_title))
        },
        emptyState = {
            CharactersEmptyState()
        },
        content = {
            Text("List of characters here will be soon!")
        }
    )
}

@Composable
private fun CharactersScreenScaffold(
    uiState: CharactersUiState,
    topBar: @Composable () -> Unit = {},
    emptyState: @Composable () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    Scaffold { paddingValues ->
        Column {
            topBar()

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (uiState.characters.isEmpty()) {
                    emptyState()
                } else {
                    content()
                }
            }
        }
    }
}

@Composable
@Preview
private fun EmptyCharactersScreenPreview() {
    CharactersScreenContent(uiState = CharactersUiState())
}

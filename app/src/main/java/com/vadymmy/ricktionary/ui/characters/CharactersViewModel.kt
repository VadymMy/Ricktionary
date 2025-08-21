package com.vadymmy.ricktionary.ui.characters

import com.vadymmy.ricktionary.ui.base.BaseViewModel

class CharactersViewModel : BaseViewModel<CharactersUiState, CharactersIntent, Nothing>(CharactersUiState()) {
    override fun reduceIntent(intent: CharactersIntent) {
        // TODO handle intent
    }
}

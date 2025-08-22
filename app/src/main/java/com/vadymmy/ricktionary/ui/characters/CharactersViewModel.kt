package com.vadymmy.ricktionary.ui.characters

import com.vadymmy.ricktionary.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor() : BaseViewModel<CharactersUiState, CharactersIntent, Nothing>(CharactersUiState()) {
    override fun reduceIntent(intent: CharactersIntent) {
        // TODO reduce intent
    }
}

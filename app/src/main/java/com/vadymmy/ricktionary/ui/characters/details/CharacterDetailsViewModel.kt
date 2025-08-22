package com.vadymmy.ricktionary.ui.characters.details

import com.vadymmy.ricktionary.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
) : BaseViewModel<CharacterDetailsUiState, CharacterDetailsIntent, Nothing>(CharacterDetailsUiState()) {

    override fun reduceIntent(intent: CharacterDetailsIntent) {
        // TODO reduce details intent
    }
}
